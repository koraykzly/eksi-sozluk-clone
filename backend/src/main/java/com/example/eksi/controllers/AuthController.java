package com.example.eksi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.request.LoginRequest;
import com.example.eksi.payload.request.SignupRequest;
import com.example.eksi.payload.response.JwtPair;
import com.example.eksi.payload.response.SuccessMessage;
import com.example.eksi.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtPair> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtPair jwt = authService.login(loginRequest);
        return ResponseEntity.ok(jwt);

    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessMessage> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        authService.register(signupRequest);
        return ResponseEntity.ok(new SuccessMessage("User registered successfully!"));
    }

}
