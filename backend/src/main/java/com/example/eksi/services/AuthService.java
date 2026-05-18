package com.example.eksi.services;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.eksi.domain.FollowingTags;
import com.example.eksi.domain.Tag;
import com.example.eksi.domain.User;
import com.example.eksi.domain.enums.ERole;
import com.example.eksi.domain.keys.FollowingTagsKey;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.exceptions.SignupException;
import com.example.eksi.payload.request.LoginRequest;
import com.example.eksi.payload.request.RefreshTokenRequest;
import com.example.eksi.payload.request.SignupRequest;
import com.example.eksi.payload.response.JwtPair;
import com.example.eksi.payload.response.SignupResponse;
import com.example.eksi.repositories.FollowingTagsRepository;
import com.example.eksi.repositories.TagRepository;
import com.example.eksi.repositories.UserRepository;
import com.example.eksi.security.jwt.JwtUtils;
import com.example.eksi.security.services.UserDetailsImpl;

//AuthService.java
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TagRepository tagRepository;

    private final FollowingTagsRepository followingTagsRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       TagRepository tagRepository,
                       FollowingTagsRepository followingTagsRepository,
                       PasswordEncoder encoder,
                       JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.followingTagsRepository = followingTagsRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public JwtPair login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtPair(authentication);
    }

    public JwtPair refresh(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefresh();
        if (!jwtUtils.validateJwtToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }
        if (!jwtUtils.isRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String username = jwtUtils.getUserNameFromJwtToken(refreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        return jwtUtils.generateJwtPair(userDetails);
    }

    @Transactional
    public SignupResponse register(SignupRequest signUpRequest) {
        String username = signUpRequest.getUsername().trim().toLowerCase();
        String email = signUpRequest.getEmail().trim().toLowerCase();

        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new SignupException(
                    "Signup failed",
                    Map.of("username", "Username is already taken"));
        }

        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new SignupException(
                    "Signup failed",
                    Map.of("email", "Email is already taken"));
        }

        User user = new User(
                username,
                email,
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getBirthday(),
                signUpRequest.getGender(),
                ERole.NAIVE);
        User savedUser = userRepository.save(user);
        followAllTags(savedUser);

        return new SignupResponse(
                "User registered successfully",
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail());
    }

    private void followAllTags(User user) {
        for (Tag tag : tagRepository.findAll()) {
            FollowingTags followingTags = new FollowingTags();
            followingTags.setId(new FollowingTagsKey(user.getId(), tag.getId()));
            followingTags.setUser(user);
            followingTags.setTag(tag);
            followingTagsRepository.save(followingTags);
        }
    }

}
