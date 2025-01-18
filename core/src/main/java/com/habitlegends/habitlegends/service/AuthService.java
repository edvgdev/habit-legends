package com.habitlegends.habitlegends.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.habitlegends.habitlegends.dto.AuthRequestResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthRequestResponseDTO register(AuthRequestResponseDTO registrationRequest);

    AuthRequestResponseDTO login(AuthRequestResponseDTO loginRequest, HttpServletResponse response);

    void refreshToken(HttpServletRequest request, HttpServletResponse response);

    boolean isEmailAlreadyRegistered(String email);

    void handleGoogleCallback(OAuth2AuthenticationToken token, HttpServletResponse response);

    void logoutUser(HttpServletRequest request, HttpServletResponse response);

    boolean isAuthenticated();
}
