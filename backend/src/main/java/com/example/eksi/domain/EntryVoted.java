package com.example.eksi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.eksi.domain.enums.EVote;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "entry_votes")
public class EntryVoted {
	
	@EmbeddedId
	private EntryVotedKey id;
	
	@ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
    @MapsId("entryId")
    @JoinColumn(name = "entry_id")
	private Entry entry;

	@Column
	private LocalDateTime datetime;

	@Enumerated(EnumType.ORDINAL)
	private EVote type;

	@PrePersist
	public void prePersist() {
		this.datetime = LocalDateTime.now();
	}
}

@Embeddable
class EntryVotedKey implements Serializable {

	private static final long serialVersionUID = -7363807410153846453L;

	@Column(name = "user_id")
    Long userId;

    @Column(name = "entry_id")
    Long entryId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}
