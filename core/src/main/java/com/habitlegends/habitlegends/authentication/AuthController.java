package com.habitlegends.habitlegends.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    public ResponseEntity<AuthRequestResponseDTO> login(@RequestBody AuthRequestResponseDTO loginRequest,
            HttpServletResponse response) {
        return ResponseEntity.ok().body(authService.login(loginRequest, response));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        authService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Logout user by clearing cookies
        authService.logoutUser(request, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/google/callback")
    public ResponseEntity<?> googleCallback(OAuth2AuthenticationToken token, HttpServletResponse response) {
        authService.handleGoogleCallback(token, response);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "http://localhost:3000").build();
    }

    @GetMapping("/is-email-existing/{email}")
    public ResponseEntity<Boolean> isEmailAlreadyRegistered(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(authService.isEmailAlreadyRegistered(email));
    }

    @GetMapping("/is-authenticated")
    public ResponseEntity<Boolean> isAuthenticated() {
        return ResponseEntity.ok().body(authService.isAuthenticated());
    }
}
