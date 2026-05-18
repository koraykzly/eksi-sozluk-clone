package com.example.eksi.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Topic;
import com.example.eksi.domain.enums.ERole;
import com.example.eksi.payload.response.SearchItemDto;
import com.example.eksi.repositories.projections.IPopularTopic;
import com.example.eksi.repositories.projections.ITopic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findById(Long id);

    Optional<Topic> findByTitle(String title);

    Optional<Topic> findByTitleIgnoreCase(String title);

    @Query(value = """
            SELECT new com.example.eksi.payload.response.SearchItemDto(t.id, t.title)
            FROM Topic t
            WHERE LOWER(t.title) LIKE CONCAT('%', :query, '%')
            ORDER BY
                CASE
                    WHEN LOWER(t.title) = :query THEN 0
                    WHEN LOWER(t.title) LIKE CONCAT(:query, '%') THEN 1
                    ELSE 2
                END,
                t.entryCountSinceMidnight DESC,
                t.lastEntered DESC
            """)
    List<SearchItemDto> searchTopics(String query, Pageable pageable);

    @Query(value = """
            SELECT new com.example.eksi.payload.response.SearchItemDto(t.id, t.title)
            FROM Topic t
            WHERE (:hasQuery = false OR LOWER(t.title) LIKE CONCAT('%', :query, '%'))
              AND (
                  :filterByEntries = false
                  OR EXISTS (
                      SELECT e.id
                      FROM Entry e
                      WHERE e.topic = t
                        AND (:hasUsername = false OR LOWER(e.user.username) = :username)
                        AND e.createdAt >= :startDatetime
                        AND e.createdAt <= :endDatetime
                  )
              )
              AND (
                  :favoriteOnly = false
                  OR EXISTS (
                      SELECT ef.id
                      FROM EntryFavorited ef
                      WHERE ef.entry.topic = t
                        AND ef.user.id = :userId
                        AND ef.entry.createdAt >= :startDatetime
                        AND ef.entry.createdAt <= :endDatetime
                  )
              )
            ORDER BY
                CASE WHEN :order = 'alphabetical' THEN t.title ELSE '' END ASC,
                CASE WHEN :order = 'entry_count_desc' THEN (
                    SELECT COUNT(eo.id)
                    FROM Entry eo
                    WHERE eo.topic = t
                ) ELSE 0 END DESC,
                CASE WHEN :order = 'descending' THEN t.lastEntered ELSE null END DESC,
                CASE
                    WHEN :order = 'default' AND :hasQuery = true AND LOWER(t.title) = :query THEN 0
                    WHEN :order = 'default' AND :hasQuery = true AND LOWER(t.title) LIKE CONCAT(:query, '%') THEN 1
                    WHEN :order = 'default' THEN 2
                    ELSE 0
                END ASC,
                t.entryCountSinceMidnight DESC,
                t.lastEntered DESC
            """,
            countQuery = """
            SELECT COUNT(t)
            FROM Topic t
            WHERE (:hasQuery = false OR LOWER(t.title) LIKE CONCAT('%', :query, '%'))
              AND (
                  :filterByEntries = false
                  OR EXISTS (
                      SELECT e.id
                      FROM Entry e
                      WHERE e.topic = t
                        AND (:hasUsername = false OR LOWER(e.user.username) = :username)
                        AND e.createdAt >= :startDatetime
                        AND e.createdAt <= :endDatetime
                  )
              )
              AND (
                  :favoriteOnly = false
                  OR EXISTS (
                      SELECT ef.id
                      FROM EntryFavorited ef
                      WHERE ef.entry.topic = t
                        AND ef.user.id = :userId
                        AND ef.entry.createdAt >= :startDatetime
                        AND ef.entry.createdAt <= :endDatetime
                  )
              )
            """)
    Page<SearchItemDto> searchTopicsAdvanced(String query,
                                             boolean hasQuery,
                                             String username,
                                             boolean hasUsername,
                                             boolean filterByEntries,
                                             LocalDateTime startDatetime,
                                             LocalDateTime endDatetime,
                                             boolean favoriteOnly,
                                             Long userId,
                                             String order,
                                             Pageable pageable);

    @Query(value = """
            SELECT
                t.id topicId,
                t.title topicTitle,
                t.entryCountSinceMidnight entryCountSinceMidnight
            FROM Topic t
            ORDER BY t.lastEntered DESC
            """,
            countQuery = """
            SELECT COUNT(t)
            FROM Topic t
            """)
    Page<ITopic> getTodayTopics(Pageable pageable);

    @Query(value = """
            SELECT
                tt.topic.id topicId,
                tt.topic.title topicTitle,
                tt.topic.entryCountSinceMidnight entryCountSinceMidnight
            FROM TopicTags tt
            JOIN FollowingTags ft
                ON ft.tag = tt.tag
                AND ft.user.id = :userId
            GROUP BY
                tt.topic.id,
                tt.topic.title,
                tt.topic.entryCountSinceMidnight,
                tt.topic.lastEntered
            ORDER BY tt.topic.lastEntered DESC
            """,
            countQuery = """
            SELECT COUNT(DISTINCT tt.topic.id)
            FROM TopicTags tt
            JOIN FollowingTags ft
                ON ft.tag = tt.tag
                AND ft.user.id = :userId
            """)
    Page<ITopic> getTodayTopicsByFollowingTags(Long userId, Pageable pageable);

    @Query(value = """
            SELECT
                t.id topicId,
                t.title topicTitle,
                t.entryCountSinceMidnight entryCountSinceMidnight
            FROM Topic t
            LEFT JOIN t.user u
            WHERE u.role = :role
            ORDER BY t.lastEntered DESC
            """,
            countQuery = """
            SELECT COUNT(t)
            FROM Topic t
            LEFT JOIN t.user u
            WHERE u.role = :role
            """)
    Page<ITopic> getTodayTopicsByUserRole(ERole role, Pageable pageable);

    @Query(value = """
            SELECT
                t.id topicId,
                t.title topicTitle,
                COUNT(e.id) entryCountLast24Hours
            FROM Topic t
            JOIN Entry e
                ON e.topic = t
                AND e.createdAt >= :since
            GROUP BY t.id, t.title
            ORDER BY MAX(e.createdAt) DESC, t.id DESC
            """,
            countQuery = """
            SELECT COUNT(DISTINCT t.id)
            FROM Topic t
            JOIN Entry e
                ON e.topic = t
                AND e.createdAt >= :since
            """)
    Page<IPopularTopic> getPopularTopics(LocalDateTime since, Pageable pageable);

    @Query(value = """
            SELECT
                tt.topic.id topicId,
                tt.topic.title topicTitle,
                tt.topic.entryCountSinceMidnight entryCountSinceMidnight
            FROM TopicTags tt
            WHERE LOWER(tt.tag.name) = LOWER(:tagName)
            ORDER BY tt.topic.lastEntered DESC, tt.topic.id DESC
            """,
            countQuery = """
            SELECT COUNT(tt)
            FROM TopicTags tt
            WHERE LOWER(tt.tag.name) = LOWER(:tagName)
            """)
    Page<ITopic> getTopicsByTag(String tagName, Pageable pageable);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
            UPDATE Topic
            SET entryCountSinceMidnight = 0
            """)
    int resetEntryCountSinceMidnight();
}
