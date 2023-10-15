package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IEntrySingle {

    public String getContent();

    public LocalDateTime getDateTime();

    public int getFavCount();

    public Long getId();

    public String getUsername();
}
