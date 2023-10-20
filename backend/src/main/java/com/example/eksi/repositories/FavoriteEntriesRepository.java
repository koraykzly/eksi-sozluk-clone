package com.example.eksi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.EntryFavorited;
import com.example.eksi.domain.keys.FavoriteEntriesKey;
import com.example.eksi.repositories.projections.IEntry;

public interface FavoriteEntriesRepository extends JpaRepository<EntryFavorited, FavoriteEntriesKey> {

    @Query("""
            SELECT
                e.id id,
                e.content content,
                t.id topicId,
                t.title topicTitle,
                u.username username,
                e.favCount favCount,
                e.dateTime dateTime
            FROM EntryFavorited ef
            LEFT JOIN ef.entry e
            LEFT JOIN e.topic t
            LEFT JOIN ef.user uf
            LEFT JOIN e.user u
            WHERE uf.username = :username
            """)
    List<IEntry> findAllByUserUsername(String username);

//    EntryFavorited save(FavoriteEntriesKey entity);
}
