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
@Table(name = "followed_topics")
public class FollowedProblematics {
	@EmbeddedId
	private FollowedProblematicsKey id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("problematicId")
	@JoinColumn(name = "problematic_id")
	private Problematic problematic;

	@Column
	private LocalDateTime datetime;

	@PrePersist
	public void prePersist() {
		this.datetime = LocalDateTime.now();
	}
}

@Embeddable
class FollowedProblematicsKey implements Serializable {

	private static final long serialVersionUID = -5026618073550544165L;

	@Column(name = "user_id")
	Long userId;

	@Column(name = "problematic_id")
	Long problematicId;

	// standard constructors, getters, and setters
	// hashcode and equals implementation
}