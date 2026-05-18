package com.example.eksi.services;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.DraftEntry;
import com.example.eksi.domain.Topic;
import com.example.eksi.domain.User;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.response.DraftEntryDto;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.repositories.DraftEntryRepository;
import com.example.eksi.repositories.TopicRepository;
import com.example.eksi.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class DraftEntryService {

    private final DraftEntryRepository draftEntryRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final EntryService entryService;
    private final Pattern urlCheckerPattern;

    public DraftEntryService(DraftEntryRepository draftEntryRepository,
                             TopicRepository topicRepository,
                             UserRepository userRepository,
                             EntryService entryService,
                             Pattern urlCheckerPattern) {
        this.draftEntryRepository = draftEntryRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.entryService = entryService;
        this.urlCheckerPattern = urlCheckerPattern;
    }

    @Transactional
    public DraftEntryDto saveDraft(String content, Long topicId, Long userId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(
                () -> new NotFoundException("Topic not found"));

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found"));

        boolean isIncludeLink = urlCheckerPattern.matcher(content).find();

        DraftEntry draftEntry = draftEntryRepository.findByUserIdAndTopicId(userId, topicId)
                .orElseGet(() -> new DraftEntry(content, isIncludeLink, topic, user));

        LocalDateTime now = LocalDateTime.now();
        if (draftEntry.getCreatedDatetime() == null) {
            draftEntry.setCreatedDatetime(now);
        }
        draftEntry.setUpdatedDatetime(now);
        draftEntry.setContent(content);
        draftEntry.setIncludeLink(isIncludeLink);

        return new DraftEntryDto(draftEntryRepository.save(draftEntry));
    }

    public DraftEntryDto getDraft(Long topicId, Long userId) {
        DraftEntry draftEntry = draftEntryRepository.findByUserIdAndTopicId(userId, topicId)
                .orElseThrow(() -> new NotFoundException("Draft entry not found"));

        return new DraftEntryDto(draftEntry);
    }

    public Page<DraftEntryDto> getDrafts(Long userId, PaginationRequest pagination) {
        return draftEntryRepository.findAllByUserIdOrderByUpdatedDatetimeDesc(userId, pagination.toPageRequest())
                .map(DraftEntryDto::new);
    }

    @Transactional
    public void deleteDraft(Long topicId, Long userId) {
        DraftEntry draftEntry = draftEntryRepository.findByUserIdAndTopicId(userId, topicId)
                .orElseThrow(() -> new NotFoundException("Draft entry not found"));

        draftEntryRepository.delete(draftEntry);
    }

    @Transactional
    public EntryDto publishDraft(Long topicId, Long userId) {
        DraftEntry draftEntry = draftEntryRepository.findByUserIdAndTopicId(userId, topicId)
                .orElseThrow(() -> new NotFoundException("Draft entry not found"));

        return entryService.addEntry(draftEntry.getContent(), topicId, userId);
    }
}
