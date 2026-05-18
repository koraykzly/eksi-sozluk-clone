package com.example.eksi.payload.response;

public class UserFollowDto {
    private String username;
    private int followerCount;
    private int followingCount;
    private int entryCount;

    public UserFollowDto() {
    }

    public UserFollowDto(String username, int followerCount, int followingCount, int entryCount) {
        this.username = username;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.entryCount = entryCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }
}
