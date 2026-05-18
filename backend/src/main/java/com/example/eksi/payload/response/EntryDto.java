package com.example.eksi.payload.response;

import java.time.LocalDateTime;

public class EntryDto {
    private Long id;
    private String content;
    private String title;
    private int upvoted;
    private int downvoted;
    private int favCount;
    private boolean includeLink;
    private boolean includeImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EntryDto() {
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
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public boolean isIncludeLink() {
        return includeLink;
    }

    public void setIncludeLink(boolean includeLink) {
        this.includeLink = includeLink;
    }

    public boolean isIncludeImage() {
        return includeImage;
    }

    public void setIncludeImage(boolean includeImage) {
        this.includeImage = includeImage;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
