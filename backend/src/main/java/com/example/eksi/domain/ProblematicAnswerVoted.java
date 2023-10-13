package com.example.eksi.domain;

import java.time.LocalDateTime;

import com.example.eksi.domain.enums.EVote;
import com.example.eksi.domain.keys.ProblematicAnswerVotedKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "problematic_answer_voted")
public class ProblematicAnswerVoted {

    @EmbeddedId
    private ProblematicAnswerVotedKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("problematicId")
    @JoinColumn(name = "problematic_id")
    private Problematic problematic;

    @Column
    private LocalDateTime datetime;

    @Enumerated(EnumType.ORDINAL)
    private EVote type;

    @PrePersist
    public void prePersist() {
        this.datetime = LocalDateTime.now();
    }
}
