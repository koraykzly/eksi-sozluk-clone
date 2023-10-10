package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EntryVotedKey implements Serializable {

    private static final long serialVersionUID = -7363807410153846453L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "entry_id")
    Long entryId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}