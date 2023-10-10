package com.example.eksi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.HelloWorldBean;
import com.example.eksi.domain.User;
import com.example.eksi.repositories.TopicRepository;
import com.example.eksi.repositories.UserRepository;
import com.example.eksi.services.TopicService;

@RestController
@RequestMapping(value = "/api/")
public class TestController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicService topicService;

    @GetMapping(value = "/test")
    public HelloWorldBean getTest() {
        topicRepository.findAll(Pageable.ofSize(5));

        Optional<User> u = userRepository.findById(1L);
        if (u.isPresent()) {

        }

        return new HelloWorldBean("test-bean");
    }

    @GetMapping(value = "/")
    public String getRoot() {
        return "This is root";
    }

}
