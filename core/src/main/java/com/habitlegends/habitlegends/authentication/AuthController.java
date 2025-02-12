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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller class for handling authentication
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user
     * 
     * @param registrationRequest DTO containing the registration details
     * @return DTO containing the saved user details
     */
    @PostMapping("/register")
    public ResponseEntity<AuthRequestResponseDTO> register(@RequestBody AuthRequestResponseDTO registrationRequest) {
        return ResponseEntity.ok().body(authService.register(registrationRequest));
    }

    /**
     * Logs in a user
     * 
     * @param loginRequest DTO containing the login details
     * @param response     HTTP response
     * @return DTO containing the user details
     */
    @PostMapping("/login")
    public ResponseEntity<AuthRequestResponseDTO> login(@RequestBody AuthRequestResponseDTO loginRequest,
            HttpServletResponse response) {
        return ResponseEntity.ok().body(authService.login(loginRequest, response));
    }

    /**
     * Refreshes the user's token
     * 
     * @param request  HTTP request
     * @param response HTTP response
     * @return ResponseEntity
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        authService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }

    /**
     * Logs out a user
     * 
     * @param request  HTTP request
     * @param response HTTP response
     * @return ResponseEntity
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("email") String email, HttpServletRequest request,
            HttpServletResponse response) {
        // Logout user by clearing cookies
        authService.logoutUser(request, response);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles Google OAuth2 callback
     * 
     * @param token    OAuth2AuthenticationToken
     * @param response HTTP response
     * @return ResponseEntity
     */
    @GetMapping("/google/callback")
    public ResponseEntity<?> googleCallback(OAuth2AuthenticationToken token, HttpServletResponse response) {
        authService.handleGoogleCallback(token, response);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "http://localhost:3000").build();
    }

    /**
     * Checks if the provided email is already registered
     * 
     * @param email Email to check
     * @return ResponseEntity
     */
    @GetMapping("/is-email-existing/{email}")
    public ResponseEntity<Boolean> isEmailAlreadyRegistered(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(authService.isEmailAlreadyRegistered(email));
    }

    /**
     * Checks if the user is authenticated
     * 
     * @return ResponseEntity
     */
    @GetMapping("/is-authenticated")
    public ResponseEntity<Boolean> isAuthenticated() {
        return ResponseEntity.ok().body(authService.isAuthenticated());
    }
}
