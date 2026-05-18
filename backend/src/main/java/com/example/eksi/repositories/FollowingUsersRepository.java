package com.example.eksi.repositories;

import java.util.List;
import java.util.Optional;

import com.example.eksi.repositories.projections.IFollowUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eksi.domain.FollowingUsers;
import com.example.eksi.domain.keys.FollowingUsersKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowingUsersRepository
        extends JpaRepository<FollowingUsers, FollowingUsersKey> {
    List<FollowingUsers> findAllByFollowerUserUsername(String username);

    @Query(value = """
        SELECT fu
        FROM FollowingUsers fu
        WHERE fu.followerUser.username = :username
        ORDER BY fu.datetime DESC, fu.followingUser.id DESC
    """,
    countQuery = """
        SELECT COUNT(fu)
        FROM FollowingUsers fu
        WHERE fu.followerUser.username = :username
    """)
    Page<FollowingUsers> findAllByFollowerUserUsername(@Param("username") String username, Pageable pageable);
    
    List<FollowingUsers> findAllByFollowingUserUsername(String username);

    @Query(value = """
        SELECT fu
        FROM FollowingUsers fu
        WHERE fu.followingUser.username = :username
        ORDER BY fu.datetime DESC, fu.followerUser.id DESC
    """,
    countQuery = """
        SELECT COUNT(fu)
        FROM FollowingUsers fu
        WHERE fu.followingUser.username = :username
    """)
    Page<FollowingUsers> findAllByFollowingUserUsername(@Param("username") String username, Pageable pageable);

    boolean existsByFollowerUser_IdAndFollowingUser_Id(Long followerUserId, Long followingUserId);

    @Query("""
        SELECT
            fu.followerUser.username as followerUsername,
            fu.followingUser.username as followingUsername,
            fu.followingUser.followerCount as followerCount,
            fu.followerUser.followingCount as followingCount
        FROM FollowingUsers fu
        WHERE fu.followerUser.id = :followerUserId
          AND fu.followingUser.id = :followingUserId
    """)
    Optional<IFollowUser> findFollowInfo(@Param("followerUserId") Long followerUserId,
                                         @Param("followingUserId") Long followingUserId);

}
