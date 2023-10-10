package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowedTopicsKey implements Serializable {
    private static final long serialVersionUID = 2191281080884427495L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "topic_id")
    Long topicId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}