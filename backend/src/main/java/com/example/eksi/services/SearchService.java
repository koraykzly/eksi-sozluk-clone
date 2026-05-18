package com.example.eksi.services;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.response.SearchItemDto;
import com.example.eksi.payload.response.SearchResponse;
import com.example.eksi.repositories.TopicRepository;
import com.example.eksi.repositories.UserRepository;

@Service
public class SearchService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public SearchService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public SearchResponse search(String query) {
        String normalizedQuery = normalizeSearchQuery(query);
        if (normalizedQuery.isBlank()) {
            return new SearchResponse();
        }
        String likeQuery = toLikeQuery(normalizedQuery);

        return new SearchResponse(
                topicRepository.searchTopics(likeQuery, PageRequest.of(0, 8)),
                userRepository.searchUsers(likeQuery, PageRequest.of(0, 4)));
    }

    public Page<SearchItemDto> searchTopics(String query,
                                            String username,
                                            LocalDateTime startDatetime,
                                            LocalDateTime endDatetime,
                                            boolean favoriteOnly,
                                            String order,
                                            Long userId,
                                            PaginationRequest pagination) {
        String normalizedQuery = normalizeSearchQuery(query);
        String likeQuery = toLikeQuery(normalizedQuery);
        String normalizedUsername = username == null || username.isBlank()
                ? null
                : username.trim().toLowerCase(Locale.ROOT);
        String normalizedOrder = normalizeOrder(order);
        boolean hasQuery = !normalizedQuery.isBlank();
        boolean useFavoriteOnly = favoriteOnly && userId != null;
        boolean hasUsername = normalizedUsername != null;
        boolean hasDatetimeFilter = startDatetime != null || endDatetime != null;
        boolean filterByEntries = hasUsername || hasDatetimeFilter;
        LocalDateTime startBound = startDatetime != null ? startDatetime : LocalDateTime.of(1900, 1, 1, 0, 0);
        LocalDateTime endBound = endDatetime != null ? endDatetime : LocalDateTime.of(9999, 12, 31, 23, 59, 59);

        return topicRepository.searchTopicsAdvanced(
                likeQuery,
                hasQuery,
                normalizedUsername,
                hasUsername,
                filterByEntries,
                startBound,
                endBound,
                useFavoriteOnly,
                userId,
                normalizedOrder,
                pagination.toPageRequest());
    }

    private String normalizeOrder(String order) {
        if (order == null) {
            return "default";
        }

        String normalizedOrder = order.trim().toLowerCase(Locale.ROOT);
        if ("descending".equals(normalizedOrder)
                || "alphabetical".equals(normalizedOrder)
                || "entry_count_desc".equals(normalizedOrder)) {
            return normalizedOrder;
        }
        return "default";
    }

    private String normalizeSearchQuery(String query) {
        if (query == null) {
            return "";
        }
        return query.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
    }

    private String toLikeQuery(String query) {
        return query.replace(" ", "%");
    }
}
