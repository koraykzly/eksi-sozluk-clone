package com.example.eksi.controllers;

import com.example.eksi.domain.Tag;
import com.example.eksi.exceptions.UnauthorizedException;
import com.example.eksi.payload.request.InsertTopicRequest;
import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.payload.response.TopicEntries;
import com.example.eksi.repositories.projections.IDebe;
import com.example.eksi.repositories.projections.IEntryWithUsername;
import com.example.eksi.repositories.projections.IPopularTopic;
import com.example.eksi.repositories.projections.ITopic;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.EntryService;
import com.example.eksi.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/topics")
public class TopicController {

    private final TopicService topicService;
    private final EntryService entryService;

    public TopicController(TopicService topicService, EntryService entryService) {
        this.topicService = topicService;
        this.entryService = entryService;
    }

    @GetMapping("/today")
    public Page<ITopic> getTodayTopics(
            @AuthenticationPrincipal UserDetailsImpl user,
            @Valid PaginationRequest pagination) {
        return topicService.getTodayTopics(user.getId(), pagination);
    }

    @GetMapping("/today/naive")
    public Page<ITopic> getTodayTopicsFromNaiveUsers(@Valid PaginationRequest pagination) {
        return topicService.getTodayTopicsFromNaiveUsers(pagination);
    }

    @GetMapping("/popular")
    public Page<IPopularTopic> getPopularTopics(@Valid PaginationRequest pagination) {
        return topicService.getPopularTopics(pagination);
    }

    @GetMapping("/tag/{tagName}")
    public Page<ITopic> getTopicsByTag(
            @PathVariable String tagName,
            @Valid PaginationRequest pagination) {
        return topicService.getTopicsByTag(tagName, pagination);
    }

    @GetMapping("/{topicId}/tags")
    public List<Tag> getTopicTags(@PathVariable Long topicId) {
        return topicService.getTopicTags(topicId);
    }

    @PostMapping
    public EntryDto insertTopic(@Valid @RequestBody InsertTopicRequest requestBody,
                                @AuthenticationPrincipal UserDetailsImpl user) {
        return entryService.addEntry(requestBody.getEntryContent(), requestBody.getTopicTitle(), user.getId());
    }

    @GetMapping("/id/{topicId}")
    public TopicEntries getEntriesByTopicId(
            @PathVariable Long topicId,
            @RequestParam(required = false, name = "a") String action,
            @Valid PaginationRequest pagination) {
        return entryService.getEntriesByTopicId(topicId, action, pagination);
    }

    @GetMapping("/debe")
    public Page<IDebe> getDebe(@RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @AuthenticationPrincipal UserDetailsImpl user,
                               @Valid PaginationRequest pagination) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now().minusDays(1);
        if (!targetDate.equals(LocalDate.now().minusDays(1)) && user == null) {
            throw new UnauthorizedException("Authentication is required");
        }
        return entryService.getDebe(targetDate, pagination);
    }

    @GetMapping("/following")
    public Page<IEntryWithUsername> getFollowingUserEntries(
        @AuthenticationPrincipal UserDetailsImpl user,
        @Valid PaginationRequest pagination) {
        return entryService.getFollowingUserEntries(user.getUsername(), pagination);
    }
}
