package com.example.eksi.controllers;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.response.SearchItemDto;
import com.example.eksi.payload.response.SearchResponse;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<SearchResponse> search(@RequestParam String query) {
        return ResponseEntity.ok(searchService.search(query));
    }

    @GetMapping("/topics")
    public ResponseEntity<Page<SearchItemDto>> searchTopics(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String username,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDatetime,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDatetime,
            @RequestParam(defaultValue = "false") boolean favoriteOnly,
            @RequestParam(defaultValue = "default") String order,
            @Valid PaginationRequest pagination,
            @AuthenticationPrincipal UserDetailsImpl user) {
        Long userId = user != null ? user.getId() : null;
        return ResponseEntity.ok(searchService.searchTopics(
                query,
                username,
                startDatetime,
                endDatetime,
                favoriteOnly,
                order,
                userId,
                pagination));
    }
}
