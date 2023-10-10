package com.example.eksi.domain.keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowedTopicsKey implements Serializable {
    private static final long serialVersionUID = 2191281080884427495L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "topic_id")
    Long topicId;

    public FollowedTopicsKey() {
        super();
    }

    public FollowedTopicsKey(Long userId, Long topicId) {
        super();
        this.userId = userId;
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FollowedTopicsKey other = (FollowedTopicsKey) obj;
        return Objects.equals(topicId, other.topicId) && Objects.equals(userId, other.userId);
    }

}