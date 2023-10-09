package com.example.eksi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eksi.domain.Entry;
import com.example.eksi.domain.User;

public interface EntryRepository extends JpaRepository<Entry, Long> {
	List<Entry> findByUser(User username);

}
