package com.example.eksi.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EntryRequest {

    @NotBlank
    private String content;

    @Min(1)
    private Long topicId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

}
