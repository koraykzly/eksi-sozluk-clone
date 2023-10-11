package com.example.eksi.domain;

import java.time.LocalDateTime;

import com.example.eksi.domain.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 51200)
    private String content;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private boolean isIncludeLink;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private ERole writtenByRole;

    @Column
    private int upvoted;
    @Column
    private int downvoted;
    @Column
    private int favCount;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist() {
        this.dateTime = LocalDateTime.now();
    }

    public Entry(@Size(min = 1, max = 51200) String content, @NotNull boolean isIncludeLink, Topic topic, User user) {
        super();
        this.content = content;
        this.isIncludeLink = isIncludeLink;
        this.topic = topic;
        this.user = user;
        this.upvoted = 0;
        this.downvoted = 0;
        this.favCount = 0;
        this.writtenByRole = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isIncludeLink() {
        return isIncludeLink;
    }

    public void setIncludeLink(boolean isIncludeLink) {
        this.isIncludeLink = isIncludeLink;
    }

    public ERole getWrittenByRole() {
        return writtenByRole;
    }

    public void setWrittenByRole(ERole writtenByRole) {
        this.writtenByRole = writtenByRole;
    }

    public int getUpvoted() {
        return upvoted;
    }

    public void setUpvoted(int upvoted) {
        this.upvoted = upvoted;
    }

    public int getDownvoted() {
        return downvoted;
    }

    public void setDownvoted(int downvoted) {
        this.downvoted = downvoted;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
