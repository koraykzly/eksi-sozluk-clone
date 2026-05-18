package com.example.eksi.controllers;

import com.example.eksi.payload.request.EntryRequest;
import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.EntryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/entries")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public Page<IEntry> getRandomEntries(@Valid PaginationRequest pagination) {
        return entryService.getRandomEntries(pagination);
    }

    @PostMapping
    public EntryDto insertEntry(@Valid @RequestBody EntryRequest requestBody,
                                @AuthenticationPrincipal UserDetailsImpl user) {
        if (requestBody.getTopicId() != null) {
            return entryService.addEntry(requestBody.getContent(), requestBody.getTopicId(), user.getId());
        }
        return entryService.addEntry(requestBody.getContent(), requestBody.getTopicTitle(), user.getId());
    }

    @GetMapping("/{entryId}")
    public IEntry getEntry(@PathVariable Long entryId) {
        return entryService.getEntry(entryId);
    }

    @PostMapping("/favorites/{entryId}")
    public IEntry addEntryToFavorite(@PathVariable Long entryId,
                                     @AuthenticationPrincipal UserDetailsImpl user) {
        return entryService.addEntryToFavorites(user.getId(), entryId);
    }

    @GetMapping("/user/{username}")
    public Page<IEntry> getEntriesByUsernameWithPagination(@PathVariable String username,
                                                           @Valid PaginationRequest pagination) {
        return entryService.getEntriesByUsernameWithPagination(username, pagination);
    }
}
