package com.example.eksi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Tag;
import com.example.eksi.repositories.TagRepository;
import com.example.eksi.repositories.TopicRepository;
import com.example.eksi.repositories.projections.ITopic;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TagRepository tagRepository;

    // bugün
    public List<ITopic> getTodayTopics(int lastNTopic) {
        return topicRepository.getTodayTopics(lastNTopic);
    }

    public List<ITopic> getTodayTopics() {
        return topicRepository.getTodayTopics(100);
    }

    // gündem
    public List<ITopic> getPopularTopics() {
        return topicRepository.getPopularTopics(100);
    }

    public List<ITopic> searchTopics() {
        return null;
    }

    public List<Tag> getTopicTags() {
        return tagRepository.findAll();
    }
}
