package com.example.eksi.repositories.projections;

public interface IFollowUser {
    String getFollowerUsername();
    String getFollowingUsername();
    Integer getFollowerCount();
    Integer getFollowingCount();
}