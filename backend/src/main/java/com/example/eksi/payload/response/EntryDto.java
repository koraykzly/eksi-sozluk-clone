package com.example.eksi.payload.response;

import java.time.LocalDateTime;

public class EntryDto {
	private String content;
	private LocalDateTime dateTime;
	private int upvoted;
	private int downvoted;
	private int favCount;

	public EntryDto() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getUpvoted() {
		return upvoted;
	}

	public void setUpvoted(int upvoted) {
		this.upvoted = upvoted;
	}

	public int getDownvoted() {
		return downvoted;
	}

	public void setDownvoted(int downvoted) {
		this.downvoted = downvoted;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

}
