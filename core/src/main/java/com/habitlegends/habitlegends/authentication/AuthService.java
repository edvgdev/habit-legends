package com.habitlegends.habitlegends.authentication;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Service class for handling user authentication
 */
public interface AuthService {

    AuthRequestResponseDTO register(AuthRequestResponseDTO registrationRequest);

    AuthRequestResponseDTO login(AuthRequestResponseDTO loginRequest, HttpServletResponse response);

    void refreshToken(HttpServletRequest request, HttpServletResponse response);

    boolean isEmailAlreadyRegistered(String email);

    void handleGoogleCallback(OAuth2AuthenticationToken token, HttpServletResponse response);

    void logoutUser(HttpServletRequest request, HttpServletResponse response);

    boolean isAuthenticated();
}
