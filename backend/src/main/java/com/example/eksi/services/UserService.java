package com.example.eksi.services;

import java.util.List;

import com.example.eksi.domain.keys.FollowingUsersKey;
import com.example.eksi.payload.request.PaginationRequest;
import com.example.eksi.payload.response.FollowUserDto;
import com.example.eksi.payload.response.UserFollowDto;
import com.example.eksi.repositories.projections.IFollowUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.eksi.domain.FollowingUsers;
import com.example.eksi.domain.User;
import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.response.UserBasicDto;
import com.example.eksi.repositories.EntryRepository;
import com.example.eksi.repositories.FavoriteEntriesRepository;
import com.example.eksi.repositories.FollowingUsersRepository;
import com.example.eksi.repositories.UserRepository;
import com.example.eksi.repositories.projections.IEntry;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private static final String DEFAULT_PROFILE_IMAGE_URL =
            "/uploads/profile-images/default-profile-picture-light.jpg";

    private final UserRepository userRepository;

    private final EntryRepository entryRepository;

    private final FollowingUsersRepository followingUsersRepository;

    private final FavoriteEntriesRepository favoriteEntriesRepository;

    private final LocalImageStorageService imageStorageService;

    public UserService(UserRepository userRepository,
                       EntryRepository entryRepository,
                       FollowingUsersRepository followingUsersRepository,
                       FavoriteEntriesRepository favoriteEntriesRepository,
                       LocalImageStorageService imageStorageService) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
        this.followingUsersRepository = followingUsersRepository;
        this.favoriteEntriesRepository = favoriteEntriesRepository;
        this.imageStorageService = imageStorageService;
    }

    public UserBasicDto getBasicInformation(String username, String authUsername) {
        User user = getUser(username);
        UserBasicDto userBasicDto = toUserBasicDto(user);
        if (authUsername != null) {
            User authUser = getUser(authUsername);
            userBasicDto.setFollowing(followingUsersRepository.existsByFollowerUser_IdAndFollowingUser_Id(
                    authUser.getId(),
                    user.getId()));
        }
        return userBasicDto;
    }

    @Transactional
    public UserBasicDto updateBiography(Long userId, String biography) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found"));
        user.setBiography(biography);

        return toUserBasicDto(userRepository.save(user));
    }

    @Transactional
    public UserBasicDto updateProfileImage(Long userId, MultipartFile image) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found"));

        String previousImageKey = user.getProfileImageKey();
        LocalImageStorageService.StoredImage storedImage = imageStorageService.storeProfileImage(userId, image);

        user.setProfileImageKey(storedImage.key());
        user.setProfileImageUrl(storedImage.url());
        User savedUser = userRepository.save(user);

        imageStorageService.delete(previousImageKey);

        return toUserBasicDto(savedUser);
    }

    private UserBasicDto toUserBasicDto(User user) {
        UserBasicDto userBasicDto = new UserBasicDto();
        userBasicDto.setUsername(user.getUsername());
        userBasicDto.setBiography(user.getBiography());
        userBasicDto.setProfileImageUrl(getProfileImageUrl(user));
        userBasicDto.setSignupDatetime(user.getSignupDatetime());
        userBasicDto.setFollowingCount(user.getFollowingCount());
        userBasicDto.setFollowerCount(user.getFollowerCount());
        userBasicDto.setEntryCount(user.getEntryCount());
        return userBasicDto;
    }

    private String getProfileImageUrl(User user) {
        if (StringUtils.hasText(user.getProfileImageUrl())) {
            return user.getProfileImageUrl();
        }
        return DEFAULT_PROFILE_IMAGE_URL;
    }

    public Page<IEntry> getUserEntries(String username, PaginationRequest pagination) {
        return entryRepository.findAllByUsernameWithPage(username, pagination.toPageRequest());
    }

    public Page<IEntry> getUserFavoriteEntries(String username, PaginationRequest pagination) {
        return favoriteEntriesRepository.findAllByUserUsername(username, pagination.toPageRequest());
    }

    public Page<IEntry> getAuthenticatedUserFavoriteEntries(String username, PaginationRequest pagination) {
        return favoriteEntriesRepository.findAllByUserUsername(username, pagination.toPageRequest());
    }

    public Page<UserFollowDto> getUserFollowing(String username, PaginationRequest pagination) {
        User user = getUser(username);
        Page<FollowingUsers> users = followingUsersRepository.findAllByFollowerUserUsername(
                user.getUsername(),
                pagination.toPageRequest());

        return users.map(u -> toUserFollowDto(u.getFollowingUser()));
    }

    public Page<UserFollowDto> getUserFollowers(String username, PaginationRequest pagination) {
        User user = getUser(username);
        Page<FollowingUsers> users = followingUsersRepository.findAllByFollowingUserUsername(
                user.getUsername(),
                pagination.toPageRequest());

        return users.map(u -> toUserFollowDto(u.getFollowerUser()));
    }

    public List<String> getUserFollowerUsernames(String username) {
        User user = getUser(username);
        List<FollowingUsers> users = followingUsersRepository.findAllByFollowingUserUsername(user.getUsername());

        return users.stream()
                .map(u -> u.getFollowerUser().getUsername())
                .toList();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(username + " not found"));
    }

    private UserFollowDto toUserFollowDto(User user) {
        return new UserFollowDto(
                user.getUsername(),
                user.getFollowerCount(),
                user.getFollowingCount(),
                user.getEntryCount());
    }


    @Transactional
    public FollowUserDto followUser(String authUsername, String targetUsername) {
        User followerUser = userRepository.findByUsername(authUsername.toLowerCase())
            .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + authUsername));

        User followingUser = userRepository.findByUsername(targetUsername.toLowerCase())
            .orElseThrow(() -> new NotFoundException("Takip edilecek kullanıcı bulunamadı: " + targetUsername));

        if (followerUser.getId().equals(followingUser.getId())) {
            throw new IllegalArgumentException("Kullanıcı kendini takip edemez.");
        }

        boolean alreadyFollowing = followingUsersRepository.existsByFollowerUser_IdAndFollowingUser_Id(
            followerUser.getId(),
            followingUser.getId()
        );

        if (alreadyFollowing) {
            throw new IllegalArgumentException("Bu kullanıcı zaten takip ediliyor.");
        }

        FollowingUsers followingUsers = new FollowingUsers();
        followingUsers.setId(new FollowingUsersKey(followerUser.getId(), followingUser.getId()));
        followingUsers.setFollowerUser(followerUser);
        followingUsers.setFollowingUser(followingUser);

        followerUser.setFollowingCount(followerUser.getFollowingCount() + 1);
        followingUser.setFollowerCount(followingUser.getFollowerCount() + 1);

        followingUsersRepository.save(followingUsers);
        userRepository.save(followerUser);
        userRepository.save(followingUser);

        IFollowUser result = followingUsersRepository.findFollowInfo(followerUser.getId(), followingUser.getId())
            .orElseThrow(() -> new IllegalStateException("Takip bilgisi alınamadı."));

        return new FollowUserDto(
            result.getFollowerUsername(),
            result.getFollowingUsername(),
            result.getFollowerCount(),
            result.getFollowingCount(),
            true
        );
    }

    @Transactional
    public FollowUserDto unfollowUser(String authUsername, String targetUsername) {
        User followerUser = userRepository.findByUsername(authUsername.toLowerCase())
            .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + authUsername));

        User followingUser = userRepository.findByUsername(targetUsername.toLowerCase())
            .orElseThrow(() -> new NotFoundException("Takipten çıkarılacak kullanıcı bulunamadı: " + targetUsername));

        if (followerUser.getId().equals(followingUser.getId())) {
            throw new IllegalArgumentException("Kullanıcı kendini takipten çıkaramaz.");
        }

        FollowingUsersKey followingUsersKey = new FollowingUsersKey(followerUser.getId(), followingUser.getId());
        FollowingUsers followingUsers = followingUsersRepository.findById(followingUsersKey)
            .orElseThrow(() -> new IllegalArgumentException("Bu kullanıcı zaten takip edilmiyor."));

        followingUsersRepository.delete(followingUsers);

        followerUser.setFollowingCount(Math.max(0, followerUser.getFollowingCount() - 1));
        followingUser.setFollowerCount(Math.max(0, followingUser.getFollowerCount() - 1));

        userRepository.save(followerUser);
        userRepository.save(followingUser);

        return new FollowUserDto(
            followerUser.getUsername(),
            followingUser.getUsername(),
            followingUser.getFollowerCount(),
            followerUser.getFollowingCount(),
            false
        );
    }
}
