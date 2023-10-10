package com.example.eksi.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Entry;
import com.example.eksi.domain.FollowedUsers;
import com.example.eksi.domain.User;
import com.example.eksi.exceptions.UserNotFoundException;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.payload.response.EntryFavoritedDto;
import com.example.eksi.payload.response.UserBasicDto;
import com.example.eksi.repositories.EntryRepository;
import com.example.eksi.repositories.FavoriteEntriesRepository;
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

    @Autowired
    private FavoriteEntriesRepository favoriteEntriesRepository;

    public UserBasicDto getBasicInformations(String username) {
        User user = getUser(username);
        return modelMapper.map(user, UserBasicDto.class);
    }

    public List<EntryDto> getUserEntries(String username) {
        User user = getUser(username);
        List<Entry> entries = entryRepository.findByUser(user);

        return entries.stream()
                .map(entry -> modelMapper.map(entry, EntryDto.class))
                .toList();
    }

    public List<EntryFavoritedDto> getUserFavoriteEntries(String username) {
        return favoriteEntriesRepository
                .findAllByUserUsername(username);
    }

    public List<String> getUserFollowers(String username) {
        User user = getUser(username);
        List<FollowedUsers> users = followedUsersRepository.findAllByFollowerUserUsername(user.getUsername());

        return users.stream()
                .map(u -> u.getFollowedUser().getUsername())
                .toList();
    }

    public List<String> getUserFollowedBy(String username) {
        User user = getUser(username);
        List<FollowedUsers> users = followedUsersRepository.findAllByFollowedUserUsername(user.getUsername());

        return users.stream()
                .map(u -> u.getFollowerUser().getUsername())
                .toList();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username + " not found"));
    }

}
