package com.example.eksi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eksi.domain.FollowedUsers;
import com.example.eksi.domain.keys.FollowedUsersKey;

public interface FollowedUsersRepository
        extends JpaRepository<FollowedUsers, FollowedUsersKey> {
    List<FollowedUsers> findAllByFollowerUserUsername(String username);
    
    List<FollowedUsers> findAllByFollowedUserUsername(String username);
}
