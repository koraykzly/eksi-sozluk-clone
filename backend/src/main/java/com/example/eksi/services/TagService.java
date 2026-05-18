package com.example.eksi.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.eksi.domain.FollowingTags;
import com.example.eksi.domain.Tag;
import com.example.eksi.domain.User;
import com.example.eksi.domain.keys.FollowingTagsKey;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.response.TagDto;
import com.example.eksi.repositories.FollowingTagsRepository;
import com.example.eksi.repositories.TagRepository;
import com.example.eksi.repositories.UserRepository;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final FollowingTagsRepository followingTagsRepository;

    public TagService(TagRepository tagRepository,
                      UserRepository userRepository,
                      FollowingTagsRepository followingTagsRepository) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.followingTagsRepository = followingTagsRepository;
    }

    public List<TagDto> getTags(Long userId) {
        if (userId == null) {
            return tagRepository.findAll().stream()
                    .map(tag -> new TagDto(tag.getId(), tag.getName(), false))
                    .toList();
        }
        return tagRepository.findAllWithFollowState(userId);
    }

    @Transactional
    public TagDto followTag(Long userId, Integer tagId) {
        User user = getUser(userId);
        Tag tag = getTag(tagId);

        if (!followingTagsRepository.existsByUser_IdAndTag_Id(user.getId(), tag.getId())) {
            FollowingTags followingTags = new FollowingTags();
            followingTags.setId(new FollowingTagsKey(user.getId(), tag.getId()));
            followingTags.setUser(user);
            followingTags.setTag(tag);
            followingTagsRepository.save(followingTags);
        }

        return new TagDto(tag.getId(), tag.getName(), true);
    }

    @Transactional
    public TagDto unfollowTag(Long userId, Integer tagId) {
        User user = getUser(userId);
        Tag tag = getTag(tagId);
        FollowingTagsKey id = new FollowingTagsKey(user.getId(), tag.getId());

        followingTagsRepository.findById(id).ifPresent(followingTagsRepository::delete);

        return new TagDto(tag.getId(), tag.getName(), false);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private Tag getTag(Integer tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Tag not found: " + tagId));
    }
}
