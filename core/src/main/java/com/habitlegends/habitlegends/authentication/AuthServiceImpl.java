package com.habitlegends.habitlegends.authentication;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.security.JwtUtils;
import com.habitlegends.habitlegends.user.CustomUserDetailsService;
import com.habitlegends.habitlegends.user.User;
import com.habitlegends.habitlegends.user.UserAuthenticationProviderEnum;
import com.habitlegends.habitlegends.user.UserRepository;
import com.habitlegends.habitlegends.userprogress.UserProgressDTO;
import com.habitlegends.habitlegends.userprogress.UserProgressService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserProgressService userProgressService;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
            JwtUtils jwtUtils, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService,
            UserProgressService userProgressService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.userProgressService = userProgressService;
    }

    @Override
    public AuthRequestResponseDTO register(AuthRequestResponseDTO registrationRequest) {

        AuthRequestResponseDTO response = new AuthRequestResponseDTO();
        try {
            User user = new User();
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setEmail(registrationRequest.getEmail());
            user.setId(null);
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setProfilePictureLink(null);
            user.setUserPlan(null);
            user.setRole("USER");
            user.setAuthProvider(UserAuthenticationProviderEnum.LOCAL);
            user.setCreatedAt(null);
            user.setUpdatedAt(null);

            User savedUser = userRepository.save(user);
            if (savedUser.getId() != null) {
                UserProgressDTO savUserProgressDTO = createProgressForRegisteredUser(savedUser.getId());
                System.out.println(savUserProgressDTO);
                response.setMessage("User created successfully");
                response.setStatusCode(200);
            }

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AuthRequestResponseDTO login(AuthRequestResponseDTO loginRequest, HttpServletResponse response) {

        AuthRequestResponseDTO authResponse = new AuthRequestResponseDTO();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            String accessToken = jwtUtils.generateToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            setTokenCookies(response, accessToken, refreshToken);

            authResponse.setMessage("User logged in successfully");
            authResponse.setStatusCode(200);
        } catch (Exception e) {
            authResponse.setMessage(e.getMessage());
            authResponse.setStatusCode(500);
        }

        return authResponse;
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // Extract the refresh token from the cookie
        String refreshToken = getRefreshTokenFromCookies(request);
        String email = jwtUtils.extractUsername(refreshToken);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (refreshToken != null && jwtUtils.isTokenValid(refreshToken, userDetails)) {

            // Generate new tokens
            String newAccessToken = jwtUtils.generateToken(userDetails);
            String newRefreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), userDetails);

            // Set the new tokens in cookies
            setTokenCookies(response, newAccessToken, newRefreshToken);
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    @Override
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void handleGoogleCallback(OAuth2AuthenticationToken token, HttpServletResponse response) {

        OAuth2User oAuth2User = token.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String firstName = oAuth2User.getAttribute("given_name");
        String lastName = oAuth2User.getAttribute("family_name");
        String picture = oAuth2User.getAttribute("picture");

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = new User();
            user.setId(null);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(null);
            user.setUserPlan(null);
            user.setProfilePictureLink(picture);
            user.setRole("USER");
            user.setCreatedAt(null);
            user.setUpdatedAt(null);
            User savedUser = userRepository.save(user);
            UserProgressDTO savedProgress = createProgressForRegisteredUser(savedUser.getId());
            System.out.println(savedProgress);
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        String accessToken = jwtUtils.generateToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

        setTokenCookies(response, accessToken, refreshToken);
    }

    @Override
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);

            request.getSession().invalidate();
        }

        clearTokenCookies(response);
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
                authentication.isAuthenticated();
    }

    private void setTokenCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(15 * 60); // 15 minutes
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        response.addCookie(refreshTokenCookie);
    }

    private void clearTokenCookies(HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(0); // Delete the cookie
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0); // Delete the cookie
        response.addCookie(refreshTokenCookie);
    }

    private String getRefreshTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private UserProgressDTO createProgressForRegisteredUser(Long userId) {
        UserProgressDTO userProgressToSave = new UserProgressDTO();
        userProgressToSave.setUserId(userId);
        userProgressToSave.setExp(0);
        userProgressToSave.setLevel(1);

        UserProgressDTO savedProgress = userProgressService.createUserProgress(userProgressToSave);
        return savedProgress;
    }

}
