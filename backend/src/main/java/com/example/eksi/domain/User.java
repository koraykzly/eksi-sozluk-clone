package com.example.eksi.domain;

import java.time.LocalDate;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @NotNull
    private LocalDate birthday;

    @Enumerated(EnumType.ORDINAL)
    private EGender gender;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<Entry> entries;

    private int followedCount;

    private int followerCount;

    private int entryCount;

    public User(@NotNull String username, @NotNull String email, @NotNull String password,
            @NotNull LocalDate birthday, EGender gender, ERole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.role = role;
    }

    public User() {
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
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getFollowedCount() {
        return followedCount;
    }

    public void setFollowedCount(int followed_count) {
        this.followedCount = followed_count;
    }

    public int getFolloweCount() {
        return followerCount;
    }

    public void setFollowerCount(int follower_count) {
        this.followerCount = follower_count;
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

    public int getFollowerCount() {
        return followerCount;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", role=" + role + "]";
    }
}
