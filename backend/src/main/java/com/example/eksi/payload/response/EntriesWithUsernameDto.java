package com.example.eksi.payload.response;

import java.util.List;

public class EntriesWithUsernameDto {
    private String title;
    private List<List<String>> usernameEntryIdPairs;

    public EntriesWithUsernameDto(String title, List<List<String>> usernameEntryIdPairs) {
        this.title = title;
        this.usernameEntryIdPairs = usernameEntryIdPairs;
    }
}
