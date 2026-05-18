package com.example.eksi.domain.keys;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowingTagsKey implements Serializable {

    @Serial
    private static final long serialVersionUID = -1340829793506514631L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "tag_id")
    private Integer tagId;

    public FollowingTagsKey() {
    }

    public FollowingTagsKey(Long userId, Integer tagId) {
        this.userId = userId;
        this.tagId = tagId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FollowingTagsKey other = (FollowingTagsKey) obj;
        return Objects.equals(tagId, other.tagId) && Objects.equals(userId, other.userId);
    }
}
