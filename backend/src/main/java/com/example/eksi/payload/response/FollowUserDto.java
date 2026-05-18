package com.example.eksi.payload.response;

public class FollowUserDto {
    private String followerUsername;
    private String followingUsername;
    private int followerCount;
    private int followingCount;
    private boolean following;

    public FollowUserDto() {
    }

    public FollowUserDto(String followerUsername, String followingUsername,
                         int followerCount, int followingCount, boolean following) {
        this.followerUsername = followerUsername;
        this.followingUsername = followingUsername;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.following = following;
    }

    public String getFollowerUsername() {
        return followerUsername;
    }

    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }

    public String getFollowingUsername() {
        return followingUsername;
    }

    public void setFollowingUsername(String followingUsername) {
        this.followingUsername = followingUsername;
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

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
