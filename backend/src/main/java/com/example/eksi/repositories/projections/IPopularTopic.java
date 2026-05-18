package com.example.eksi.repositories.projections;

public interface IPopularTopic {
    public Long getTopicId();

    public String getTopicTitle();

    public int getEntryCountLast24Hours();
}
