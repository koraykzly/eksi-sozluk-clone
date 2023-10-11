package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IEntryFavorited {
    public Long getId();

    public String getContent();

    public String getTitle();

    public String getUsername();

    public int getFavCount();

    public LocalDateTime getDateTime();
}
