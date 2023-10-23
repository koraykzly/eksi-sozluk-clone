package com.example.eksi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eksi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);

    public Optional<User> findByUsername(String username);
    
    public Optional<User> findByEmail(String email);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String username);

}
