package com.example.eksi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Topic;
import com.example.eksi.repositories.projections.ITopic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
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
    
    Optional<Topic> findById(Long id);
}
