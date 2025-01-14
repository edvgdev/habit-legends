package com.habitlegends.habitlegends.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habitlegends.habitlegends.dto.AuthRequestResponseDTO;
import com.habitlegends.habitlegends.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRequestResponseDTO> register(@RequestBody AuthRequestResponseDTO registrationRequest) {
        return ResponseEntity.ok().body(authService.register(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRequestResponseDTO> login(@RequestBody AuthRequestResponseDTO loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthRequestResponseDTO> refreshToken(
            @RequestBody AuthRequestResponseDTO refreshTokenRequest) {
        return ResponseEntity.ok().body(authService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/is-email-existing/{email}")
    public ResponseEntity<Boolean> isEmailAlreadyRegistered(@PathVariable String email) {
        return ResponseEntity.ok().body(authService.isEmailAlreadyRegistered(email));
    }

}
