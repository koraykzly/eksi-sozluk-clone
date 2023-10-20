package com.example.eksi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Entry;
import com.example.eksi.repositories.projections.IDebe;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.repositories.projections.IEntrySingle;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.id topicId,
                t.title topicTitle,
                e.favCount favCount,
                e.dateTime dateTime,
                u.username username
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user  u
            WHERE e.id = :id
            """)
    Optional<IEntry> findByIdWithTitle(long id);

    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.id topicId,
                t.title topicTitle,
                e.favCount favCount,
                e.dateTime dateTime,
                u.username username
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
                t.id topicId,
                t.title topicTitle,
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
    Page<IEntrySingle> findAllByTopicId(Long id, PageRequest pageRequest);

    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.id topicId,
                t.title topicTitle,
                e.favCount favCount,
                e.dateTime dateTime
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user  u
            WHERE u.username = :username
            ORDER BY e.dateTime DESC
            """)
    Page<IEntry> findAllByUsernameWithPage(String username, PageRequest pageRequest);

    @Query("""
            SELECT
               MAX(e.id) id,
               MAX(e.content) content,
               MAX(t.id) topicId,
               MAX(t.title) topicTitle,
               MAX(e.favCount) favCount,
               MAX(e.dateTime) dateTime,
               u.username username
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user  u
            WHERE e.favCount > :favCount
            GROUP BY u.username
            ORDER BY u.username
            LIMIT 11
            """)
    List<IEntry> findRandomEntriesWithFavCountGreaterThan(int favCount);
    // it's not random, fix later

    @Query("""
            SELECT
                e.id entryId,
                t.id topicId,
                t.title topicTitle
            FROM Entry e
            LEFT JOIN e.topic t
            ORDER BY e.upvoted DESC
            LIMIT 25
            """)
    List<IDebe> findMostUpvotedEntryTopicsFromYesterday();

}
