package com.example.eksi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.response.TagDto;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getTags(@AuthenticationPrincipal UserDetailsImpl user) {
        Long userId = user != null ? user.getId() : null;
        return ResponseEntity.ok(tagService.getTags(userId));
    }

    @PostMapping("/{tagId}/follow")
    public ResponseEntity<TagDto> followTag(
            @PathVariable Integer tagId,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(tagService.followTag(user.getId(), tagId));
    }

    @DeleteMapping("/{tagId}/follow")
    public ResponseEntity<TagDto> unfollowTag(
            @PathVariable Integer tagId,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(tagService.unfollowTag(user.getId(), tagId));
    }
}
