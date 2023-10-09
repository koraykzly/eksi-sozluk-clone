package com.example.eksi.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Entry;
import com.example.eksi.domain.FollowedUsers;
import com.example.eksi.domain.User;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.payload.response.UserBasicDto;
import com.example.eksi.repositories.EntryRepository;
import com.example.eksi.repositories.FollowedUsersRepository;
import com.example.eksi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EntryRepository entryRepository;

	@Autowired
	FollowedUsersRepository followedUsersRepository;

	@Autowired
	private ModelMapper modelMapper;

	public UserBasicDto getBasicInformations(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		if (user == null)
			return null;

		return modelMapper.map(user, UserBasicDto.class);
	}

	public List<EntryDto> getUserEntries(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		List<Entry> entries = entryRepository.findByUser(user);

		return entries.stream()
				.map(entry -> modelMapper.map(entry, EntryDto.class))
				.toList();
	}

	public List<String> getUserFollowers(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		if (user == null) {
			System.out.println("Error, user null: " + username);
			return null;
		}
		List<FollowedUsers> users = followedUsersRepository.findByFollowerUserUsername(user.getUsername());

		return users.stream()
				.map(u -> u.getFollowedUser().getUsername())
				.toList();
	}

	public List<User> getUserFollowedUSers() {
		return null;
	}
}
