package com.example.eksi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "entry_votes")
public class FavoriteEntries {
	
	@EmbeddedId
	private FavoriteEntriesKey id;
	
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

	@PrePersist
	public void prePersist() {
		this.datetime = LocalDateTime.now();
	}
}

@Embeddable
class FavoriteEntriesKey implements Serializable {

	private static final long serialVersionUID = -9149059266368185663L;

	@Column(name = "user_id")
    Long userId;

    @Column(name = "entry_id")
    Long entryId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}