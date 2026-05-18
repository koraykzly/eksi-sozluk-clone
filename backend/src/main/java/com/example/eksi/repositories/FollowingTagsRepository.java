package com.example.eksi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eksi.domain.FollowingTags;
import com.example.eksi.domain.keys.FollowingTagsKey;

public interface FollowingTagsRepository extends JpaRepository<FollowingTags, FollowingTagsKey> {
    boolean existsByUser_IdAndTag_Id(Long userId, Integer tagId);
}
