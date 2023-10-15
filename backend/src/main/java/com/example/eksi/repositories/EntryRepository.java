package com.example.eksi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Entry;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.repositories.projections.IEntrySingle;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.title title,
                e.favCount favCount,
                e.dateTime dateTime
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user  u
            WHERE u.username = :username
            ORDER BY e.dateTime DESC
            """)
    List<IEntry> findAllByUsername(String username);

    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.title title,
                e.favCount favCount,
                e.dateTime dateTime
            FROM Entry e
            LEFT JOIN e.topic t
            WHERE e.id = :id
            """)
    Optional<IEntry> findByIdWithTopicTitle(Long id);

    @Query("""
            SELECT
                e.id id,
                e.content content,
                e.favCount favCount,
                e.dateTime dateTime,
                u.username username
            FROM Entry e
            LEFT JOIN e.user u
            WHERE e.topic.id = :id
            """)
    List<IEntrySingle> findAllByTopicId(Long id);

}
