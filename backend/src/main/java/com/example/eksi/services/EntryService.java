package com.example.eksi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.repositories.projections.IEntryWithUsername;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Entry;
import com.example.eksi.domain.EntryFavorited;
import com.example.eksi.domain.Topic;
import com.example.eksi.domain.User;
import com.example.eksi.domain.keys.FavoriteEntriesKey;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.payload.response.TopicEntries;
import com.example.eksi.repositories.DraftEntryRepository;
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

    private final TopicRepository topicRepository;

    private final EntryRepository entryRepository;

    private final FavoriteEntriesRepository favoriteEntriesRepository;

    private final DraftEntryRepository draftEntryRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final Pattern urlCheckerPattern;

    public EntryService(TopicRepository topicRepository,
                        EntryRepository entryRepository,
                        FavoriteEntriesRepository favoriteEntriesRepository,
                        DraftEntryRepository draftEntryRepository,
                        UserRepository userRepository,
                        ModelMapper modelMapper,
                        Pattern urlCheckerPattern) {
        this.topicRepository = topicRepository;
        this.entryRepository = entryRepository;
        this.favoriteEntriesRepository = favoriteEntriesRepository;
        this.draftEntryRepository = draftEntryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.urlCheckerPattern = urlCheckerPattern;
    }

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
        draftEntryRepository.findByUserIdAndTopicId(userId, topicId)
                .ifPresent(draftEntryRepository::delete);

        EntryDto entryDto = modelMapper.map(entry, EntryDto.class);
        entryDto.setTitle(topic.getTitle());
        return entryDto;
    }

    // add entry with create topic
    @Transactional
    public EntryDto addEntry(String content, String topicTitle, Long userId) {
        String normalizedTopicTitle = normalizeTopicTitle(topicTitle);

        Topic topic = topicRepository.findByTitleIgnoreCase(normalizedTopicTitle).orElse(null);
        if (topic != null) {
            return addEntry(content, topic.getId(), userId);
        }

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found"));

        topic = new Topic(normalizedTopicTitle, user);
        topic = topicRepository.save(topic);

        boolean isIncludeLink = checkContentIncludeLink(content);

        Entry entry = entryRepository.save(new Entry(content, isIncludeLink, topic, user));
        EntryDto entryDto = modelMapper.map(entry, EntryDto.class);
        entryDto.setTitle(topic.getTitle());
        return entryDto;

    }

    private String normalizeTopicTitle(String topicTitle) {
        return topicTitle.trim().toLowerCase();
    }

    private boolean checkContentIncludeLink(String content) {
        return urlCheckerPattern.matcher(content).find();
    }

    public IEntry getEntry(Long id) {
        return entryRepository.findByIdWithTitle(id).orElseThrow(
                () -> new NotFoundException("Entry not found"));

    }

    public IEntry addEntryToFavorites(Long userId, Long entryId) {
        User user = new User();
        user.setId(userId);

        Entry entry = new Entry();
        entry.setId(entryId);

        FavoriteEntriesKey id = new FavoriteEntriesKey();
        id.setEntryId(entryId);
        id.setUserId(userId);

        EntryFavorited favoriteEntry = new EntryFavorited();
        favoriteEntry.setId(id);
        favoriteEntry.setUser(user);
        favoriteEntry.setEntry(entry);

        favoriteEntriesRepository.save(favoriteEntry);

        return entryRepository.findByIdWithTopicTitle(entryId).orElse(null);
    }

    public TopicEntries getEntriesByTopicId(Long topicId, PaginationRequest pagination) {
        return getEntriesByTopicId(topicId, null, pagination);
    }

    public TopicEntries getEntriesByTopicId(Long topicId, String action, PaginationRequest pagination) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(
                () -> new NotFoundException("Topic not found"));

        Page<IEntrySingle> entries;
        if ("popular".equals(action)) {
            entries = entryRepository.findRecentByTopicId(
                    topicId,
                    LocalDateTime.now().minusHours(24),
                    pagination.toPageRequest()
            );
        } else if ("today".equals(action)) {
            entries = entryRepository.findRecentByTopicId(
                    topicId,
                    LocalDate.now().atStartOfDay(),
                    pagination.toPageRequest()
            );
        } else {
            entries = entryRepository.findAllByTopicId(topicId, pagination.toPageRequest());
        }

        return new TopicEntries(topic.getId(), topic.getTitle(), entries);

    }

    public Page<IEntry> getEntriesByUsernameWithPagination(String username, PaginationRequest pagination) {
        return entryRepository.findAllByUsernameWithPage(username, pagination.toPageRequest());

    }
    
    public Page<IEntry> getRandomEntries(PaginationRequest pagination) {
        return entryRepository.findRandomEntriesWithFavCountGreaterThan(
            40, pagination.toPageRequest(11));
    }

    public Page<IDebe> getDebe(LocalDate date, PaginationRequest pagination) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return entryRepository.findMostUpvotedEntryTopicsByDate(
                start,
                end,
                pagination.toPageRequest(25)
        );
    }

    public Page<IEntryWithUsername> getFollowingUserEntries(String username, PaginationRequest pagination) {
        return entryRepository.findFollowingUserEntries(username, pagination.toPageRequest());
    }
}
