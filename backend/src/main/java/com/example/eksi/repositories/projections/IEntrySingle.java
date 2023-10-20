package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IEntrySingle {
    public Long getId();

    public String getContent();

    public int getFavCount();
    
    public LocalDateTime getDateTime();
    
    public String getUsername();
}
