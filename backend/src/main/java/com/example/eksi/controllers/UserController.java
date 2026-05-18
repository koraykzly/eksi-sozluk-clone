package com.example.eksi.controllers;

import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.request.UpdateBiographyRequest;
import com.example.eksi.payload.response.FollowUserDto;
import com.example.eksi.payload.response.UserFollowDto;
import com.example.eksi.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.eksi.payload.response.UserBasicDto;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserBasicDto> getUser(
            @PathVariable String username,
            @AuthenticationPrincipal UserDetailsImpl authenticatedUser) {
        String authUsername = authenticatedUser != null ? authenticatedUser.getUsername() : null;
        UserBasicDto user = userService.getBasicInformation(username, authUsername);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}/entries")
    public ResponseEntity<Page<IEntry>> getUserEntries(
            @PathVariable String username,
            @Valid PaginationRequest pagination) {
        Page<IEntry> entries = userService.getUserEntries(username, pagination);
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{username}/following")
    public ResponseEntity<Page<UserFollowDto>> getUserFollowing(
            @PathVariable String username,
            @Valid PaginationRequest pagination) {
        Page<UserFollowDto> list = userService.getUserFollowing(username, pagination);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<Page<UserFollowDto>> getUserFollowers(
            @PathVariable String username,
            @Valid PaginationRequest pagination) {
        Page<UserFollowDto> list = userService.getUserFollowers(username, pagination);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{username}/favorites")
    public ResponseEntity<Page<IEntry>> getUserFavoriteEntries(
            @PathVariable String username,
            @Valid PaginationRequest pagination) {
        Page<IEntry> entries = userService.getUserFavoriteEntries(username, pagination);
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/me/favorites")
    public ResponseEntity<Page<IEntry>> getAuthenticatedUserFavoriteEntries(
            @AuthenticationPrincipal UserDetailsImpl user,
            @Valid PaginationRequest pagination) {
        Page<IEntry> entries = userService.getAuthenticatedUserFavoriteEntries(user.getUsername(), pagination);
        return ResponseEntity.ok(entries);
    }

    @PatchMapping("/me/biography")
    public ResponseEntity<UserBasicDto> updateAuthenticatedUserBiography(
            @AuthenticationPrincipal UserDetailsImpl user,
            @Valid @RequestBody UpdateBiographyRequest requestBody) {
        UserBasicDto updatedUser = userService.updateBiography(user.getId(), requestBody.getBiography());
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping(value = "/me/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserBasicDto> updateAuthenticatedUserProfileImage(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam("image") MultipartFile image) {
        UserBasicDto updatedUser = userService.updateProfileImage(user.getId(), image);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/{username}/follow")
    public ResponseEntity<FollowUserDto> followUser(
        @PathVariable String username, @AuthenticationPrincipal UserDetailsImpl user) {
        String authUsername = user.getUsername();
        return ResponseEntity.ok(userService.followUser(authUsername, username));
    }

    @DeleteMapping("/{username}/follow")
    public ResponseEntity<FollowUserDto> unfollowUser(
        @PathVariable String username, @AuthenticationPrincipal UserDetailsImpl user) {
        String authUsername = user.getUsername();
        return ResponseEntity.ok(userService.unfollowUser(authUsername, username));
    }

}
