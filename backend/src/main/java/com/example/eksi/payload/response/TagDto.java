package com.example.eksi.payload.response;

public class TagDto {
    private int id;
    private String name;
    private boolean following;

    public TagDto() {
    }

    public TagDto(int id, String name, boolean following) {
        this.id = id;
        this.name = name;
        this.following = following;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
