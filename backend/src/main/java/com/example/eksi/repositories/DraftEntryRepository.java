package com.example.eksi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eksi.domain.DraftEntry;

public interface DraftEntryRepository extends JpaRepository<DraftEntry, Long> {

    @EntityGraph(attributePaths = "topic")
    Optional<DraftEntry> findByUserIdAndTopicId(Long userId, Long topicId);

    @EntityGraph(attributePaths = "topic")
    Page<DraftEntry> findAllByUserIdOrderByUpdatedDatetimeDesc(Long userId, Pageable pageable);
}
