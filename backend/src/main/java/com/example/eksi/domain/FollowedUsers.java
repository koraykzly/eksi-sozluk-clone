package com.example.eksi.domain;

import java.time.LocalDateTime;

import com.example.eksi.domain.keys.FollowedUsersKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "followed_users")
public class FollowedUsers {

    @EmbeddedId
    private FollowedUsersKey id;

    @ManyToOne
    @MapsId("followerUserId")
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    @ManyToOne
    @MapsId("followedUserId")
    @JoinColumn(name = "followed_user_id")
    private User followedUser;

    @Column
    private LocalDateTime datetime;

    @PrePersist
    public void prePersist() {
        this.datetime = LocalDateTime.now();
    }

    public FollowedUsersKey getId() {
        return id;
    }

    public void setId(FollowedUsersKey id) {
        this.id = id;
    }

    public User getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(User followerUser) {
        this.followerUser = followerUser;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

}
