package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IEntrySingle {
    public Long getId();

    public String getContent();

    public int getFavCount();

    public boolean isIncludeLink();

    public boolean isIncludeImage();
    
    public LocalDateTime getCreatedAt();

    public LocalDateTime getUpdatedAt();
    
    public String getUsername();
}
