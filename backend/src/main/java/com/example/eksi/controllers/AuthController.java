package com.example.eksi.controllers;

import java.net.URI;

import com.example.eksi.payload.request.LoginRequest;
import com.example.eksi.payload.request.RefreshTokenRequest;
import com.example.eksi.payload.request.SignupRequest;
import com.example.eksi.payload.response.JwtPair;
import com.example.eksi.payload.response.MeResponse;
import com.example.eksi.payload.response.SignupResponse;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.AuthService;
import com.example.eksi.services.EntryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtPair> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtPair> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        logger.info("refresh token {}\n", refreshTokenRequest.getRefresh());
        return ResponseEntity.ok(authService.refresh(refreshTokenRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        SignupResponse signupResponse = authService.register(signupRequest);
        URI location = URI.create("/api/users/" + signupResponse.getUsername());
        return ResponseEntity.created(location).body(signupResponse);
    }

    @GetMapping("/me")
    public MeResponse me(@AuthenticationPrincipal UserDetailsImpl user) {
        return new MeResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
