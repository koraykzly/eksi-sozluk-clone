package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProblematicAnswersVotedKey implements Serializable {

    private static final long serialVersionUID = 3108748429405574981L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "problematic_answer_id")
    Long problematicAnswerId;

}
