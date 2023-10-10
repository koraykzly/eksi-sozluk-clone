package com.example.eksi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Topic;
import com.example.eksi.repositories.TopicRepository;

@Service
public class EntryService {

    @Autowired
    TopicRepository topicRepository;

    public List<Topic> getEntries(Long topic_id) {
        return null;
    }

    public List<Topic> searchInEntries(Long topic_id) {
        return null;
    }

}
