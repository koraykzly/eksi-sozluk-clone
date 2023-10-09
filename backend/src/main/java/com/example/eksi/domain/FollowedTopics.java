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
public class FollowedTopics {
	@EmbeddedId
	private FollowedTopicsKey id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("topicId")
	@JoinColumn(name = "topic_id")
	private Topic topic;

	@Column
	private LocalDateTime datetime;

	@PrePersist
	public void prePersist() {
		this.datetime = LocalDateTime.now();
	}
}

@Embeddable
class FollowedTopicsKey implements Serializable {
	private static final long serialVersionUID = 2191281080884427495L;

	@Column(name = "user_id")
	Long userId;

	@Column(name = "topic_id")
	Long topicId;

	// standard constructors, getters, and setters
	// hashcode and equals implementation
}