package com.example.eksi.payload.response;

import java.time.LocalDateTime;

public class UserBasicDto {
    private String username;
    private String biography;
    private String profileImageUrl;
    private LocalDateTime signupDatetime;
    private boolean following;
    private int followingCount;
    private int followerCount;
    private int entryCount;

    public UserBasicDto() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public LocalDateTime getSignupDatetime() {
        return signupDatetime;
    }

    public void setSignupDatetime(LocalDateTime signupDatetime) {
        this.signupDatetime = signupDatetime;
    }

    public boolean getFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

}
