package com.example.eksi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Entry;
import com.example.eksi.domain.EntryFavorited;
import com.example.eksi.domain.Topic;
import com.example.eksi.domain.User;
import com.example.eksi.domain.keys.FavoriteEntriesKey;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.payload.response.TopicEntries;
import com.example.eksi.repositories.EntryRepository;
import com.example.eksi.repositories.FavoriteEntriesRepository;
import com.example.eksi.repositories.TopicRepository;
import com.example.eksi.repositories.UserRepository;
import com.example.eksi.repositories.projections.IDebe;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.repositories.projections.IEntrySingle;

import jakarta.transaction.Transactional;

@Service
public class EntryService {

    private static final Logger logger = LoggerFactory.getLogger(EntryService.class);

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    FavoriteEntriesRepository favoriteEntriesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Pattern urlCheckerPattern;

    public List<Topic> searchInEntries(String keyword) {
        return null;
    }

    // add entry to topic already exists
    @Transactional
    public EntryDto addEntry(String content, Long topicId, Long userId) {

        boolean isIncludeLink = checkContentIncludeLink(content);

        Topic topic = topicRepository.findById(topicId).orElseThrow(
                () -> new NotFoundException("Topic not found"));

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found"));

        // update topic lastEntered
        topic.setLastEntered(LocalDateTime.now());
        topic.increaseEntryCountSinceMidnight();

        Entry entry = entryRepository.save(new Entry(content, isIncludeLink, topic, user));

        EntryDto entryDto = modelMapper.map(entry, EntryDto.class);
        entryDto.setTitle(topic.getTitle());
        return entryDto;
    }

    // add entry with create topic
    @Transactional
    public EntryDto addEntry(String content, String topicTitle, Long userId) {

        // if entry already exists
        Topic topic = topicRepository.findByTitle(topicTitle).orElse(null);
        if (topic != null) {
            return addEntry(content, topic.getId(), userId);
        }

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found"));

        topic = new Topic(topicTitle, user);
        topic = topicRepository.save(topic);

        boolean isIncludeLink = checkContentIncludeLink(content);

        Entry entry = entryRepository.save(new Entry(content, isIncludeLink, topic, user));
        EntryDto entryDto = modelMapper.map(entry, EntryDto.class);
        entryDto.setTitle(topic.getTitle());
        return entryDto;

    }

    private boolean checkContentIncludeLink(String content) {
        return urlCheckerPattern.matcher(content).find();
    }

    public IEntry getEntry(Long id) {
        return entryRepository.findByIdWithTitle(id).orElseThrow(
                () -> new NotFoundException("Entry not found"));

    }

    public IEntry addEntryToFavorities(Long userId, Long entryId) {

        User user = new User();
        user.setId(userId);

        Entry entry = new Entry();
        entry.setId(entryId);

        FavoriteEntriesKey id = new FavoriteEntriesKey();
        id.setEntryId(entryId);
        id.setUserId(userId);

        EntryFavorited favoritedEntry = new EntryFavorited();
        favoritedEntry.setId(id);
        favoritedEntry.setUser(user);
        favoritedEntry.setEntry(entry);

        favoriteEntriesRepository.save(favoritedEntry);

        return entryRepository.findByIdWithTopicTitle(entryId).orElse(null);
    }

    public TopicEntries getEntriesByTopicId(Long topicId, int page, int size) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(
                () -> new NotFoundException("Topic not found"));

        Sort sort = Sort.by(Sort.Direction.ASC, "dateTime");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<IEntrySingle> entries = entryRepository.findAllByTopicId(topicId, pageRequest);

        return new TopicEntries(topic.getId(), topic.getTitle(), entries);

    }

    public Page<IEntry> getEntriesByUsernameWithPagination(String username) {
        Sort sort = Sort.by(Sort.Direction.ASC, "dateTime");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);
        return entryRepository.findAllByUsernameWithPage(username, pageRequest);

    }
    
    public List<IEntry> getRandomEntries() {
        return entryRepository.findRandomEntriesWithFavCountGreaterThan(40);
        
    }
    
    public List<IDebe> getDebe() {
        return entryRepository.findMostUpvotedEntryTopicsFromYesterday();
    }

}
