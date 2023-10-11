package com.example.eksi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.repositories.projections.ITopic;
import com.example.eksi.services.TopicService;

@RestController
@RequestMapping(value = "/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping(value = "/today")
    public List<ITopic> getTodayTopics() {
        return topicService.getTodayTopics();
    }

    @GetMapping(value = "/popular")
    public List<ITopic> getPopularTopics() {
        return topicService.getPopularTopics();
    }

}
