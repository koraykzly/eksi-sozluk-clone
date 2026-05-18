package com.example.eksi.payload.response;

public class SearchItemDto {
    private Long id;
    private String name;

    public SearchItemDto() {
    }

    public SearchItemDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
