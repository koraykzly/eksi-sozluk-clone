package com.example.eksi.payload.response;

import java.time.LocalDateTime;

public class EntryDtoForTopic {
    Long id;
    private String content;
    private int favCount;
    private LocalDateTime dateTime;
    private String username;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getFavCount() {
        return favCount;
    }
    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
