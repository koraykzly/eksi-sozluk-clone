package com.example.eksi.payload.request;

import jakarta.validation.constraints.NotBlank;

public class InsertTopicRequest {
    @NotBlank
    private String entryContent;

    @NotBlank
    private String topicTitle;

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
    
    
}
