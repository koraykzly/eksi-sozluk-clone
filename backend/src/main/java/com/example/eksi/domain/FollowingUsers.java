package com.example.eksi.domain;

import java.time.LocalDateTime;

import com.example.eksi.domain.keys.FollowingUsersKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "following_users")
public class FollowingUsers {

    @EmbeddedId
    private FollowingUsersKey id;

    @ManyToOne
    @MapsId("followerUserId")
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    @ManyToOne
    @MapsId("followingUserId")
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    @Column
    private LocalDateTime datetime;

    @PrePersist
    public void prePersist() {
        this.datetime = LocalDateTime.now();
    }

    public FollowingUsersKey getId() {
        return id;
    }

    public void setId(FollowingUsersKey id) {
        this.id = id;
    }

    public User getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(User followerUser) {
        this.followerUser = followerUser;
    }

    public User getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(User followingUser) {
        this.followingUser = followingUser;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

}
