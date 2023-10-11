package com.example.eksi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eksi.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public boolean existsByName(String name);

    public List<Tag> findAll();
}
