package com.example.eksi.repositories;

import java.util.Optional;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.User;
import com.example.eksi.payload.response.SearchItemDto;

public interface UserRepository extends JpaRepository<User, Long> {

    @NullMarked
    public Optional<User> findById(Long id);

    public Optional<User> findByUsername(String username);

    Optional<User> findByUsernameIgnoreCase(String username);

    @Query("""
            SELECT new com.example.eksi.payload.response.SearchItemDto(u.id, u.username)
            FROM User u
            WHERE LOWER(u.username) LIKE CONCAT('%', :query, '%')
            ORDER BY
                CASE
                    WHEN LOWER(u.username) = :query THEN 0
                    WHEN LOWER(u.username) LIKE CONCAT(:query, '%') THEN 1
                    ELSE 2
                END,
                u.followerCount DESC,
                u.entryCount DESC
            """)
    List<SearchItemDto> searchUsers(String query, Pageable pageable);
    
    public Optional<User> findByEmail(String email);

    public boolean existsByUsername(String username);

    public boolean existsByUsernameIgnoreCase(String username);

    public boolean existsByEmail(String username);

    public boolean existsByEmailIgnoreCase(String email);

}
