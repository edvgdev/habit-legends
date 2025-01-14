package com.habitlegends.habitlegends.service.impl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.AuthRequestResponseDTO;
import com.habitlegends.habitlegends.entity.User;
import com.habitlegends.habitlegends.repository.UserRepository;
import com.habitlegends.habitlegends.service.AuthService;
import com.habitlegends.habitlegends.util.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
            JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
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
            user.setCreatedAt(null);
            user.setUpdatedAt(null);

            User savedUser = userRepository.save(user);
            if (savedUser.getId() != null) {
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
    public AuthRequestResponseDTO login(AuthRequestResponseDTO loginRequest) {
        AuthRequestResponseDTO response = new AuthRequestResponseDTO();

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AuthRequestResponseDTO refreshToken(AuthRequestResponseDTO refreshTokenRequest) {
        AuthRequestResponseDTO response = new AuthRequestResponseDTO();
        try {
            String userEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            User user = userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
                var jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    @Override
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
