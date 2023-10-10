package com.example.eksi.payload.response;

import java.time.LocalDateTime;

public interface EntryFavoritedDto {
    public String getContent();

    public String getTitle();

    public String getUsername();

    public int getFavCount();

    public LocalDateTime getDateTime();
}
