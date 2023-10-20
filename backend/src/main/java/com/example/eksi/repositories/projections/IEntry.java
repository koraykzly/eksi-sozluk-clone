package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IEntry {
    public Long getId();

    public String getContent();

    public String getTopicTitle();
    
    public Long getTopicId();

    public int getFavCount();

    public LocalDateTime getDateTime();

    public String getUsername();
}
