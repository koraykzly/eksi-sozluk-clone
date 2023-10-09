package com.example.eksi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tag")
public class Tag {

	@Id
	private short id;

	@NotNull
	private String name;

	public Tag(short id, @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
