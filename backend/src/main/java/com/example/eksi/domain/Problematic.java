package com.example.eksi.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "problematic")
public class Problematic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Size(min = 1, max = 51200)
    private String content;
    
    @Size(min = 1, max = 512)
    private String title;

    @Column
    private LocalDateTime datetime;

    @PrePersist
    public void prePersist() {
        this.datetime = LocalDateTime.now();
    }

    @Column
    private int upvoted;

    @Column
    private int downvoted;
}
