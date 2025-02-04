package com.habitlegends.habitlegends.authentication;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.habitlegends.habitlegends.security.JwtUtils;
import com.habitlegends.habitlegends.user.CustomUserDetailsService;
import com.habitlegends.habitlegends.user.User;
import com.habitlegends.habitlegends.user.UserAuthenticationProviderEnum;
import com.habitlegends.habitlegends.user.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;

    public Oauth2SuccessHandler(JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService,
            UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = token.getPrincipal();

        // Extract user details
        String email = oAuth2User.getAttribute("email");
        String firstName = oAuth2User.getAttribute("given_name");
        String lastName = oAuth2User.getAttribute("family_name");
        String picture = oAuth2User.getAttribute("picture");

        // Check if the user exists in the database
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            // Create a new user if they don't exist
            user = new User();
            user.setId(null);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(null); // No password for OAuth2 users
            user.setUserPlan(null);
            user.setProfilePictureLink(picture);
            user.setRole("USER");
            user.setCreatedAt(null);
            user.setUpdatedAt(null);
            user.setAuthProvider(UserAuthenticationProviderEnum.GOOGLE);
            userRepository.save(user);
        }

        // Load user details and generate JWT tokens
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        String accessToken = jwtUtils.generateToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

        // Set tokens in cookies
        setTokenCookies(response, accessToken, refreshToken);

        // Redirect to a stateless endpoint
        // Redirect to the frontend with a success query parameter
        response.sendRedirect("http://localhost:3000?login=success");
    }

    private void setTokenCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        // Set access token cookie
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(15 * 60); // 15 minutes
        response.addCookie(accessTokenCookie);

        // Set refresh token cookie
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        response.addCookie(refreshTokenCookie);
    }
}