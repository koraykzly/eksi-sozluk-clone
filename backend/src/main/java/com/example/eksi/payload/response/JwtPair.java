package com.example.eksi.payload.response;

public class JwtPair {
    private String access;
    private String refresh;
    private String username;

    public JwtPair(String access, String refresh, String username) {
        this.access = access;
        this.refresh = refresh;
        this.username = username;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
