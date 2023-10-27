package com.example.eksi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eksi.domain.Problematic;
import com.example.eksi.repositories.projections.IProblematic;
import com.example.eksi.repositories.projections.IProblematicAnswer;
import com.example.eksi.repositories.projections.IProblematicTitle;
import com.example.eksi.repositories.projections.IProblematicTitleWithCount;

public interface ProblematicRepository extends JpaRepository<Problematic, Long> {

    @Query("""
            SELECT
                p.id problematicId,
                p.title problematicTitle,
                t.id topicId,
                t.title topicTitle,
                COUNT(pa.datetime) lastEntered
            FROM ProblematicAnswers pa
            LEFT JOIN pa.problematic p
            LEFT JOIN p.topic t
            GROUP BY p.id, p.title, t.id, t.title
            ORDER BY MAX(pa.datetime) DESC, COUNT(pa.id) DESC
            LIMIT 20
            """)
    List<IProblematicTitleWithCount> findPopularProblematics();

    @Query("""
            SELECT
                p.id problematicId,
                p.title problematicTitle,
                t.id topicId,
                t.title topicTitle
            FROM ProblematicAnswers pa
            LEFT JOIN pa.problematic p
            LEFT JOIN p.topic t
            GROUP BY p.id, p.title, t.id, t.title
            ORDER BY MAX(pa.datetime) DESC
            LIMIT 20
            """)
    List<IProblematicTitle> findTodayProblematics();

    @Query("""
             SELECT
                p.id problematicId,
                p.title problematicTitle,
                t.id topicId,
                t.title topicTitle,
                p.upvoted upvoted,
                p.downvoted downvoted,
                p.datetime datetime,
                p.content content,
                u.username username,
                u.id userId
            FROM Problematic p
            LEFT JOIN p.topic t
            LEFT JOIN p.user u
            WHERE p.id = :id
            """)
    Optional<IProblematic> findByIdWithTopic(Long id);

    @Query("""
             SELECT
                pa.id answerId,
                pa.content content,
                pa.datetime datetime,
                u.id userId,
                u.username username,
                pa.upvoted upvoted,
                pa.downvoted downvoted
            FROM ProblematicAnswers pa
            LEFT JOIN pa.user u
            WHERE pa.problematic.id = :problematicId
            """)
    Page<IProblematicAnswer> findAllProblematicAnswers(Long problematicId, PageRequest pageRequest);

}
