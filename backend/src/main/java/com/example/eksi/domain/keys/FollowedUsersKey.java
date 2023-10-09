package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowedUsersKey implements Serializable {

	private static final long serialVersionUID = -7090690964533635946L;

	@Column(name = "follower_user_id")
	Long followerUserId;

	@Column(name = "followed_user_id")
	Long followedUserId;

	// standard constructors, getters, and setters
	// hashcode and equals implementation
}