package com.example.eksi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.User;
import com.example.eksi.domain.enums.ERole;
import com.example.eksi.exceptions.SignupException;
import com.example.eksi.payload.request.LoginRequest;
import com.example.eksi.payload.request.SignupRequest;
import com.example.eksi.payload.response.JwtPair;
import com.example.eksi.repositories.UserRepository;
import com.example.eksi.security.jwt.JwtUtils;

//AuthService.java
@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtPair login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtPair(authentication);
    }

    public boolean register(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new SignupException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new SignupException("Email is already taken!");
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getBirthday(),
                signUpRequest.getGender(),
                ERole.NAIVE);
        userRepository.save(user);

        return true;
    }

}
