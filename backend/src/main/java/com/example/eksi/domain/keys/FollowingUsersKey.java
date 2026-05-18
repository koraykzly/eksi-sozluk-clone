package com.example.eksi.domain.keys;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowingUsersKey implements Serializable {

    @Serial
    private static final long serialVersionUID = -7090690964533635946L;

    @Column(name = "follower_user_id")
    Long followerUserId;

    @Column(name = "following_user_id")
    Long followingUserId;

    public FollowingUsersKey() {
        super();
    }

    public FollowingUsersKey(Long followerUserId, Long followingUserId) {
        super();
        this.followerUserId = followerUserId;
        this.followingUserId = followingUserId;
    }

    public Long getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(Long followerUserId) {
        this.followerUserId = followerUserId;
    }

    public Long getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(Long followingUserId) {
        this.followingUserId = followingUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followingUserId, followerUserId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FollowingUsersKey other = (FollowingUsersKey) obj;
        return Objects.equals(followingUserId, other.followingUserId)
                && Objects.equals(followerUserId, other.followerUserId);
    }

}