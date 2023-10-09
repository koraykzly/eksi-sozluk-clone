package com.example.eksi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.payload.response.UserBasicDto;
import com.example.eksi.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/{username}")
	public ResponseEntity<UserBasicDto> getUser(@PathVariable String username) {
		UserBasicDto user = userService.getBasicInformations(username);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping("/{username}/entries")
	public ResponseEntity<List<EntryDto>> getUserEntries(@PathVariable String username) {
		List<EntryDto> entries = userService.getUserEntries(username);
		return ResponseEntity.ok(entries);
	}

	@GetMapping("/{username}/followers")
	public ResponseEntity<List<String>> getUserFollowers(@PathVariable String username) {
		List<String> list = userService.getUserFollowers(username);
		return ResponseEntity.ok(list);
	}

}
