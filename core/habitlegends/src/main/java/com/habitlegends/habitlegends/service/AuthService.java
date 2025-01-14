package com.habitlegends.habitlegends.service;

import com.habitlegends.habitlegends.dto.AuthRequestResponseDTO;

public interface AuthService {

    AuthRequestResponseDTO register(AuthRequestResponseDTO registrationRequest);

    AuthRequestResponseDTO login(AuthRequestResponseDTO loginRequest);

    AuthRequestResponseDTO refreshToken(AuthRequestResponseDTO refreshTokenRequest);

    boolean isEmailAlreadyRegistered(String email);
}
