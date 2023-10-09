package com.example.eksi.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "problematic_answers")
public class ProblematicAnswers {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "problematic_id")
	private Problematic problematic;

	@Size(min = 1, max = 51200)
	private String content;

	@Column
	private LocalDateTime datetime;

	@PrePersist
	public void prePersist() {
		this.datetime = LocalDateTime.now();
	}

	@Column
	private int upvoted;

	@Column
	private int downvoted;
}
