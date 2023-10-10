package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TopicTagsKey implements Serializable {

    private static final long serialVersionUID = 1224014275776556246L;

    @Column(name = "tag_id")
    short tagId;

    @Column(name = "topic_id")
    Long topicId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}
