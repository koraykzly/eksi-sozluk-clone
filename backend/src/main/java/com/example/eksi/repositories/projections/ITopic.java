package com.example.eksi.repositories.projections;

public interface ITopic {
    public Long getTopicId();

    public String getTopicTitle();

    public int getEntryCountSinceMidnight();
}
