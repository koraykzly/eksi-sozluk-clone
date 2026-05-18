package com.example.eksi.payload.response;

import java.time.LocalDateTime;

import com.example.eksi.domain.DraftEntry;

public class DraftEntryDto {
    private Long id;
    private String content;
    private boolean includeLink;
    private Long topicId;
    private String topicTitle;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    public DraftEntryDto() {
    }

    public DraftEntryDto(DraftEntry draftEntry) {
        this.id = draftEntry.getId();
        this.content = draftEntry.getContent();
        this.includeLink = draftEntry.isIncludeLink();
        this.topicId = draftEntry.getTopic().getId();
        this.topicTitle = draftEntry.getTopic().getTitle();
        this.createdDatetime = draftEntry.getCreatedDatetime();
        this.updatedDatetime = draftEntry.getUpdatedDatetime();
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

    public boolean isIncludeLink() {
        return includeLink;
    }

    public void setIncludeLink(boolean includeLink) {
        this.includeLink = includeLink;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public LocalDateTime getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}
