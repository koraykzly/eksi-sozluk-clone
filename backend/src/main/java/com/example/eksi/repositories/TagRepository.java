package com.example.eksi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eksi.domain.Tag;
import com.example.eksi.payload.response.TagDto;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    public boolean existsByName(String name);

    public List<Tag> findAll();

    @Query("""
            SELECT tt.tag
            FROM TopicTags tt
            WHERE tt.topic.id = :topicId
            ORDER BY tt.tag.name ASC
            """)
    List<Tag> findAllByTopicId(@Param("topicId") Long topicId);

    @Query("""
            SELECT new com.example.eksi.payload.response.TagDto(
                t.id,
                t.name,
                CASE WHEN COUNT(ft.id.userId) > 0 THEN true ELSE false END
            )
            FROM Tag t
            LEFT JOIN FollowingTags ft
                ON ft.tag = t
                AND ft.user.id = :userId
            GROUP BY t.id, t.name
            ORDER BY t.name ASC
            """)
    List<TagDto> findAllWithFollowState(@Param("userId") Long userId);
}
