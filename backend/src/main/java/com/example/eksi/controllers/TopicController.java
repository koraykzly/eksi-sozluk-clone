package com.example.eksi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.domain.Tag;
import com.example.eksi.payload.request.InsertTopicRequest;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.payload.response.TopicEntries;
import com.example.eksi.repositories.projections.ITopic;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.EntryService;
import com.example.eksi.services.TopicService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private EntryService entryService;

    @GetMapping(value = "/today")
    public List<ITopic> getTodayTopics() {
        return topicService.getTodayTopics();
    }

    @GetMapping(value = "/popular")
    public List<ITopic> getPopularTopics() {
        return topicService.getPopularTopics();
    }

    @GetMapping(value = "/tags")
    public List<Tag> getTopicTags() {
        return topicService.getTopicTags();
    }

    @PostMapping(value = "/")
    public EntryDto insertTopic(@Valid @RequestBody InsertTopicRequest requestBody) {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        return entryService.addEntry(
                requestBody.getEntryContent(),
                requestBody.getTopicTitle(),
                user.getId());

    }

    @GetMapping(value = "id/{topicId}")
    public TopicEntries getEntriesByTopicId(
            @PathVariable Long topicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return entryService.getEntriesByTopicId(topicId, page, size);

    }

}
