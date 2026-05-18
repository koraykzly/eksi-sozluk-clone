package com.example.eksi.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.eksi.repositories.projections.IEntryWithUsername;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Entry;
import com.example.eksi.repositories.projections.IDebe;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.repositories.projections.IEntrySingle;
import org.springframework.data.repository.query.Param;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.id topicId,
                t.title topicTitle,
                e.favCount favCount,
                e.isIncludeLink includeLink,
                e.isIncludeImage includeImage,
                e.createdAt createdAt,
                e.updatedAt updatedAt,
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
                e.isIncludeLink includeLink,
                e.isIncludeImage includeImage,
                e.createdAt createdAt,
                e.updatedAt updatedAt,
                u.username username
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user  u
            WHERE u.username = :username
            ORDER BY e.createdAt ASC, e.id ASC
            """)
    List<IEntry> findAllByUsername(String username);

    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.id topicId,
                t.title topicTitle,
                e.favCount favCount,
                e.isIncludeLink includeLink,
                e.isIncludeImage includeImage,
                e.createdAt createdAt,
                e.updatedAt updatedAt
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
                e.isIncludeLink includeLink,
                e.isIncludeImage includeImage,
                e.createdAt createdAt,
                e.updatedAt updatedAt,
                u.username username
            FROM Entry e
            LEFT JOIN e.user u
            WHERE e.topic.id = :id
            ORDER BY e.createdAt ASC, e.id ASC
            """)
    Page<IEntrySingle> findAllByTopicId(Long id, Pageable pageable);

    @Query("""
            SELECT
                e.id id,
                e.content content,
                e.favCount favCount,
                e.isIncludeLink includeLink,
                e.isIncludeImage includeImage,
                e.createdAt createdAt,
                e.updatedAt updatedAt,
                u.username username
            FROM Entry e
            LEFT JOIN e.user u
            WHERE e.topic.id = :id
              AND e.createdAt >= :since
            ORDER BY e.createdAt ASC, e.id ASC
            """)
    Page<IEntrySingle> findRecentByTopicId(
            @Param("id") Long id,
            @Param("since") LocalDateTime since,
            Pageable pageable
    );

    @Query("""
             SELECT
                e.id id,
                e.content content,
                t.id topicId,
                t.title topicTitle,
                e.favCount favCount,
                e.isIncludeLink includeLink,
                e.isIncludeImage includeImage,
                e.createdAt createdAt,
                e.updatedAt updatedAt
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user  u
            WHERE u.username = :username
            ORDER BY e.createdAt DESC, e.id DESC
            """)
    Page<IEntry> findAllByUsernameWithPage(String username, Pageable pageable);

    @Query("""
            SELECT
               e.id id,
               e.content content,
               t.id topicId,
               t.title topicTitle,
               e.favCount favCount,
               e.isIncludeLink includeLink,
               e.isIncludeImage includeImage,
               e.createdAt createdAt,
               e.updatedAt updatedAt,
               u.username username
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user u
            WHERE e.favCount > :favCount
            ORDER BY function('random')
            """)
    Page<IEntry> findRandomEntriesWithFavCountGreaterThan(@Param("favCount") int favCount, Pageable pageable);
    // it's not random, fix later

    @Query(value = """
            SELECT
                e.id as entryId,
                t.id as topicId,
                t.title as topicTitle
            FROM Entry e
            LEFT JOIN e.topic t
            WHERE e.createdAt >= :start
              AND e.createdAt < :end
            ORDER BY e.upvoted DESC, e.id DESC
        """,
        countQuery = """
            SELECT COUNT(e)
            FROM Entry e
            WHERE e.createdAt >= :start
              AND e.createdAt < :end
        """)
    Page<IDebe> findMostUpvotedEntryTopicsByDate(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );


    @Query(value = """
        SELECT
            e.id as entryId,
            t.id as topicId,
            e.content as content,
            t.title as topicTitle,
            e.favCount as favCount,
            e.isIncludeLink as includeLink,
            e.isIncludeImage as includeImage,
            e.createdAt as createdAt,
            e.updatedAt as updatedAt,
            fu.followingUser.username as username
        FROM Entry e
        LEFT JOIN e.topic t
        LEFT JOIN e.user u
        LEFT JOIN FollowingUsers fu ON fu.followingUser = u
        WHERE fu.followerUser.username = :username
        ORDER BY e.createdAt DESC, e.id DESC
    """,
    countQuery = """
        SELECT COUNT(e)
        FROM Entry e
        LEFT JOIN e.user u
        LEFT JOIN FollowingUsers fu ON fu.followingUser = u
        WHERE fu.followerUser.username = :username
    """)
    Page<IEntryWithUsername> findFollowingUserEntries(String username, Pageable pageable);

}
