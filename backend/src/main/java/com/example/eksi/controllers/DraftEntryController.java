package com.example.eksi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.request.EntryRequest;
import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.response.DraftEntryDto;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.DraftEntryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/drafts")
public class DraftEntryController {

    private final DraftEntryService draftEntryService;

    public DraftEntryController(DraftEntryService draftEntryService) {
        this.draftEntryService = draftEntryService;
    }

    @PostMapping
    public DraftEntryDto saveDraft(@Valid @RequestBody EntryRequest requestBody,
                                   @AuthenticationPrincipal UserDetailsImpl user) {
        return draftEntryService.saveDraft(requestBody.getContent(), requestBody.getTopicId(), user.getId());
    }

    @GetMapping
    public Page<DraftEntryDto> getDrafts(@AuthenticationPrincipal UserDetailsImpl user,
                                         @Valid PaginationRequest pagination) {
        return draftEntryService.getDrafts(user.getId(), pagination);
    }

    @GetMapping("/{topicId}")
    public DraftEntryDto getDraft(@PathVariable Long topicId,
                                  @AuthenticationPrincipal UserDetailsImpl user) {
        return draftEntryService.getDraft(topicId, user.getId());
    }

    @DeleteMapping("/{topicId}")
    public void deleteDraft(@PathVariable Long topicId,
                            @AuthenticationPrincipal UserDetailsImpl user) {
        draftEntryService.deleteDraft(topicId, user.getId());
    }
}
