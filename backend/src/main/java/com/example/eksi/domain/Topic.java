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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, message = "Enter atleast 1 character")
    @Size(max = 256, message = "Enter maximum 256 characters")
    @Column(unique = true)
    private String title;

    @NotNull
    private LocalDateTime createdDatetime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime lastEntered;

    @Column
    private int entryCountSinceMidnight;

    public Topic(Long id,
            @Size(min = 1, message = "Enter atleast 1 character") @Size(max = 256, message = "Enter maximum 256 characters") String title,
            @NotNull LocalDateTime createdDatetime, User user, LocalDateTime lastEntered, int entryCountSinceMidnight) {
        super();
        this.id = id;
        this.title = title;
        this.createdDatetime = createdDatetime;
        this.user = user;
        this.lastEntered = lastEntered;
        this.entryCountSinceMidnight = entryCountSinceMidnight;
    }

    public Topic(
            @Size(min = 1, message = "Enter atleast 1 character") @Size(max = 256, message = "Enter maximum 256 characters") String title,
            User user) {
        super();
        this.title = title;
        this.user = user;
    }

    public Topic() {
        super();
    }

    @PrePersist
    public void prePersist() {
        this.createdDatetime = LocalDateTime.now();
        this.lastEntered = LocalDateTime.now();
        this.entryCountSinceMidnight = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
