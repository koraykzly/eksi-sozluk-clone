package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProblematicVotedKey implements Serializable {

    private static final long serialVersionUID = -1512602665450501483L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "problematic_id")
    Long problematicId;

}
