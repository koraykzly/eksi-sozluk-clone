package com.example.eksi.repositories.projections;

public interface ITopic {
    public String getTitle();
    
    public int getId();

    public int getEntryCountSinceMidnight();
}
