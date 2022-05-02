package com.example.mpvehicle.entities;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.util.Objects;

public class InstagramUser {
    private String username;
    private String fullName;
    private String profilePicUrl;

    public InstagramUser(String username, String fullName, String profilePicUrl) {
        this.username = username;
        this.fullName = fullName;
        this.profilePicUrl = profilePicUrl;
    }

    public static InstagramUser from(InstagramUserSummary userSummary) {
        return new InstagramUser(userSummary.getUsername(),
                userSummary.getFull_name(),
                userSummary.getProfile_pic_url());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstagramUser that = (InstagramUser) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "InstagramUser{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }
}
