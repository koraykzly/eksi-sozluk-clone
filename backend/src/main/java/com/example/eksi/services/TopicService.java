package com.example.eksi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Tag;
import com.example.eksi.domain.enums.ERole;
import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.repositories.TagRepository;
import com.example.eksi.repositories.TopicRepository;
import com.example.eksi.repositories.projections.IPopularTopic;
import com.example.eksi.repositories.projections.ITopic;

@Service
public class TopicService {

    private static final int TOPIC_LIST_PAGE_SIZE = 50;

    private final TopicRepository topicRepository;

    private final TagRepository tagRepository;

    public TopicService(TopicRepository topicRepository, TagRepository tagRepository) {
        this.topicRepository = topicRepository;
        this.tagRepository = tagRepository;
    }

    public Page<ITopic> getTodayTopics(Long userId, PaginationRequest pagination) {
        return topicRepository.getTodayTopicsByFollowingTags(userId, pagination.toPageRequest(TOPIC_LIST_PAGE_SIZE));
    }

    public Page<ITopic> getTodayTopicsFromNaiveUsers(PaginationRequest pagination) {
        return topicRepository.getTodayTopicsByUserRole(ERole.NAIVE, pagination.toPageRequest());
    }

    public Page<IPopularTopic> getPopularTopics(PaginationRequest pagination) {
        return topicRepository.getPopularTopics(
                LocalDateTime.now().minusHours(24),
                pagination.toPageRequest(TOPIC_LIST_PAGE_SIZE));
    }

    public Page<ITopic> getTopicsByTag(String tagName, PaginationRequest pagination) {
        return topicRepository.getTopicsByTag(tagName, pagination.toPageRequest());
    }

    public List<Tag> getTopicTags(Long topicId) {
        return tagRepository.findAllByTopicId(topicId);
    }

    public List<ITopic> searchTopics() {
        return null;
    }

}
