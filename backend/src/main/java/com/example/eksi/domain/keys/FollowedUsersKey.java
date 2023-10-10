package com.example.eksi.domain.keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowedUsersKey implements Serializable {

    private static final long serialVersionUID = -7090690964533635946L;

    @Column(name = "follower_user_id")
    Long followerUserId;

    @Column(name = "followed_user_id")
    Long followedUserId;

    public FollowedUsersKey() {
        super();
    }

    public FollowedUsersKey(Long followerUserId, Long followedUserId) {
        super();
        this.followerUserId = followerUserId;
        this.followedUserId = followedUserId;
    }

    public Long getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(Long followerUserId) {
        this.followerUserId = followerUserId;
    }

    public Long getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(Long followedUserId) {
        this.followedUserId = followedUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followedUserId, followerUserId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FollowedUsersKey other = (FollowedUsersKey) obj;
        return Objects.equals(followedUserId, other.followedUserId)
                && Objects.equals(followerUserId, other.followerUserId);
    }

}