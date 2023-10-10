package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowedProblematicsKey implements Serializable {

    private static final long serialVersionUID = -5026618073550544165L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "problematic_id")
    Long problematicId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}