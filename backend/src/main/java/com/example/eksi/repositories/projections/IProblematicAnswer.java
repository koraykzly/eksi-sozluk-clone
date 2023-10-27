package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IProblematicAnswer {
    Long getAnswerId();

    String getContent();

    LocalDateTime getDatetime();

    Long getUserId();

    String getUsername();

    int getUpvoted();

    int getDownvoted();
}
