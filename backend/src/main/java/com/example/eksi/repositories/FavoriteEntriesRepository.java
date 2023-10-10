package com.example.eksi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.EntryFavorited;
import com.example.eksi.domain.keys.FavoriteEntriesKey;
import com.example.eksi.payload.response.EntryFavoritedDto;

public interface FavoriteEntriesRepository extends JpaRepository<EntryFavorited, FavoriteEntriesKey> {

    @Query("""
            SELECT 
                e.content content, 
                t.title title,
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
    List<EntryFavoritedDto> findAllByUserUsername(String username);

}
