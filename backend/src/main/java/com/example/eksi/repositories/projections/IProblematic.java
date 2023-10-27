package com.example.eksi.repositories.projections;

import java.time.LocalDateTime;

public interface IProblematic {
    Long getProblematicId();

    String getProblematicTitle();

    Long getTopicId();

    String getTopicTitle();

    int getUpvoted();

    int getDownvoted();

    LocalDateTime getDatetime();

    String getContent();

    Long getUserId();

    String getUsername();

}
