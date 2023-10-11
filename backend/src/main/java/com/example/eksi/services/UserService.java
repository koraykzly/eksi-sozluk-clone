package com.example.eksi.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.FollowedUsers;
import com.example.eksi.domain.User;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.response.UserBasicDto;
import com.example.eksi.repositories.EntryRepository;
import com.example.eksi.repositories.FavoriteEntriesRepository;
import com.example.eksi.repositories.FollowedUsersRepository;
import com.example.eksi.repositories.UserRepository;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.repositories.projections.IEntryFavorited;

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

    public List<IEntry> getUserEntries(String username) {
        return entryRepository.findAllByUsername(username);
    }

    public List<IEntryFavorited> getUserFavoriteEntries(String username) {
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
                () -> new NotFoundException(username + " not found"));
    }

}
