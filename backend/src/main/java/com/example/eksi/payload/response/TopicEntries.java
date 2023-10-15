package com.example.eksi.payload.response;

import java.util.List;

public class TopicEntries {
    private Long id;
    private String title;
    private List<EntryDtoForTopic> entries;
    
    

    public TopicEntries(Long id, String title, List<EntryDtoForTopic> entries) {
        super();
        this.id = id;
        this.title = title;
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<EntryDtoForTopic> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryDtoForTopic> entries) {
        this.entries = entries;
    }

}

/*
class EntryDtoT {
    private Long id;
    private String content;
    private String username;
    private String favCount;
    private LocalDateTime datetime;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFavCount() {
        return favCount;
    }

    public void setFavCount(String favCount) {
        this.favCount = favCount;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

}
*/
