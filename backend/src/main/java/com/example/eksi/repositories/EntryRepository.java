package com.example.eksi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Entry;
import com.example.eksi.repositories.projections.IEntry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("""
             SELECT
                e.id id,
                e.content content, 
                t.title title,
                e.favCount favCount,
                e.dateTime dateTime
            FROM Entry e
            LEFT JOIN e.topic t
            LEFT JOIN e.user  u
            WHERE u.username = :username
            ORDER BY e.dateTime DESC
            """)
    List<IEntry> findAllByUsername(String username);

}
