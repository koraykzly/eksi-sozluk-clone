package com.example.eksi.payload.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EntryRequest {

    @NotBlank
    private String content;

    @Min(1)
    @Nullable
    private Long topicId;

    private String topicTitle;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Nullable
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(@Nullable Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @AssertTrue(message = "Either topicId or topicTitle is required")
    public boolean isTopicSpecified() {
        return topicId != null || (topicTitle != null && !topicTitle.isBlank());
    }
}
