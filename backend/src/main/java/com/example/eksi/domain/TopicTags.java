package com.example.eksi.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "topic_tags")
public class TopicTags {
	@EmbeddedId
	private TopicTagsKey id;

	@ManyToOne
	@MapsId("tagId")
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@ManyToOne
	@MapsId("topicId")
	@JoinColumn(name = "topic_id")
	private Topic entry;

}

@Embeddable
class TopicTagsKey implements Serializable {

	private static final long serialVersionUID = 1224014275776556246L;

	@Column(name = "tag_id")
	short tagId;

	@Column(name = "topic_id")
	Long topicId;

	// standard constructors, getters, and setters
	// hashcode and equals implementation
}