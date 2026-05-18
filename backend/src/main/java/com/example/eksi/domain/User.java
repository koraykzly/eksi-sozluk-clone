package com.example.eksi.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.eksi.domain.enums.EGender;
import com.example.eksi.domain.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    private String biography;

    private String profileImageUrl;

    private String profileImageKey;

    @NotNull
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime signupDatetime;

    @NotNull
    private LocalDate birthday;

    @Enumerated(EnumType.ORDINAL)
    private EGender gender;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Entry> entries;

    private int followingCount;

    private int followerCount;

    private int entryCount;

    public User(@NotNull String username, @NotNull String email, @NotNull String password,
            @NotNull LocalDate birthday, EGender gender, ERole role) {
        setUsername(username);
        setEmail(email);
        this.password = password;
        this.signupDatetime = LocalDateTime.now();
        this.birthday = birthday;
        this.gender = gender;
        this.role = role;
    }

    public User() {
    }

    @PrePersist
    public void prePersist() {
        if (signupDatetime == null) {
            signupDatetime = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = normalizeLowercase(username);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = normalizeLowercase(email);
    }

    private String normalizeLowercase(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageKey() {
        return profileImageKey;
    }

    public void setProfileImageKey(String profileImageKey) {
        this.profileImageKey = profileImageKey;
    }

    public LocalDateTime getSignupDatetime() {
        return signupDatetime;
    }

    public void setSignupDatetime(LocalDateTime signupDatetime) {
        this.signupDatetime = signupDatetime;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", role=" + role + "]";
    }
}
