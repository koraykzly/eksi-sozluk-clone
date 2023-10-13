package com.example.eksi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Topic;
import com.example.eksi.repositories.projections.ITopic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findById(Long id);

    Optional<Topic> findByTitle(String title);

    @Query("""
            SELECT
                t.id id,
                t.title title,
                t.entryCountSinceMidnight entryCountSinceMidnight
            FROM Topic t
            ORDER BY t.lastEntered DESC
            LIMIT :lastNTopic
            """)
    List<ITopic> getTodayTopics(int lastNTopic);

    @Query("""
            SELECT
                t.id id,
                t.title title,
                t.entryCountSinceMidnight entryCountSinceMidnight
            FROM Topic t
            ORDER BY t.entryCountSinceMidnight DESC, t.lastEntered DESC
            LIMIT :lastNTopic
            """)
    List<ITopic> getPopularTopics(int lastNTopic);

    @Query("""
            UPDATE Topic
            SET entryCountSinceMidnight = 0
            """)
    void setZeroEntryCountSinceMidnight();
}
