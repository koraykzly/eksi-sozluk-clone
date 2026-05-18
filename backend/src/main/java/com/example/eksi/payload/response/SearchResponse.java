package com.example.eksi.payload.response;

import java.util.Collections;
import java.util.List;

public class SearchResponse {
    private List<SearchItemDto> topics;
    private List<SearchItemDto> users;

    public SearchResponse() {
        this.topics = Collections.emptyList();
        this.users = Collections.emptyList();
    }

    public SearchResponse(List<SearchItemDto> topics, List<SearchItemDto> users) {
        this.topics = topics;
        this.users = users;
    }

    public List<SearchItemDto> getTopics() {
        return topics;
    }

    public void setTopics(List<SearchItemDto> topics) {
        this.topics = topics;
    }

    public List<SearchItemDto> getUsers() {
        return users;
    }

    public void setUsers(List<SearchItemDto> users) {
        this.users = users;
    }
}
