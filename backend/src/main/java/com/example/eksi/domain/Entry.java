package com.example.eksi.domain;

import java.time.LocalDateTime;

import com.example.eksi.domain.enums.ERole;

import jakarta.persistence.*;
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
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    private boolean isIncludeLink;

    @NotNull
    private boolean isIncludeImage;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private ERole writtenByRole;

    @Column
    private int upvoted;
    @Column
    private int downvoted;
    @Column
    private int favCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Entry(@Size(min = 1, max = 51200) String content, @NotNull boolean isIncludeLink, Topic topic, User user) {
        super();
        setContent(content);
        this.isIncludeLink = isIncludeLink;
        this.isIncludeImage = false;
        this.topic = topic;
        this.user = user;
        this.upvoted = 0;
        this.downvoted = 0;
        this.favCount = 0;
        this.writtenByRole = user.getRole();
    }

    public Entry() {
        super();
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
        this.content = normalizeContent(content);
    }

    private String normalizeContent(String content) {
        return content == null ? null : content.toLowerCase();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isIncludeLink() {
        return isIncludeLink;
    }

    public void setIncludeLink(boolean isIncludeLink) {
        this.isIncludeLink = isIncludeLink;
    }

    public boolean isIncludeImage() {
        return isIncludeImage;
    }

    public void setIncludeImage(boolean isIncludeImage) {
        this.isIncludeImage = isIncludeImage;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
