package com.example.eksi.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eksi.controllers.EntryController;
import com.example.eksi.domain.Entry;
import com.example.eksi.domain.Topic;
import com.example.eksi.domain.User;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.repositories.EntryRepository;
import com.example.eksi.repositories.TopicRepository;
import com.example.eksi.repositories.UserRepository;

@Service
public class EntryService {

    private static final Logger logger = LoggerFactory.getLogger(EntryService.class);

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Topic> getEntries(Long topic_id) {
        return null;
    }

    public List<Topic> searchInEntries(Long topic_id) {
        return null;
    }

    public EntryDto addEntry(String content, Long topicId, Long userId) {

        boolean isIncludeLink = checkContentIncludeLink(content);

        Topic topic = topicRepository.findById(topicId).orElseThrow(
                () -> new NotFoundException("Topic not found"));

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found"));

        Entry entry = entryRepository.save(new Entry(content, isIncludeLink, topic, user));
        EntryDto dto = modelMapper.map(entry, EntryDto.class);
        return dto;

    }

    private boolean checkContentIncludeLink(String content) {
        // fill later
        return false;
    }

}
