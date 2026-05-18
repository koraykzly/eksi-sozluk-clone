package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IEntry {
    public Long getId();

    public String getContent();

    public String getTopicTitle();
    
    public Long getTopicId();

    public int getFavCount();

    public boolean isIncludeLink();

    public boolean isIncludeImage();

    public LocalDateTime getCreatedAt();

    public LocalDateTime getUpdatedAt();

    public String getUsername();
}
