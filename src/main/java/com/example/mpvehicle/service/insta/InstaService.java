package com.example.mpvehicle.service.insta;

import com.example.mpvehicle.entities.InstagramUser;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class InstaService {

    @Autowired
    InstaAuthService authService;

    public List<InstagramUser> findFollowDitchersList(HttpServletRequest request) throws IOException {
        Instagram4j instagram = getInstagram(request);
        HashSet<InstagramUser> followerSet = getFollowerSet(instagram);
        HashSet<InstagramUser> followingSet = getFollowingSet(instagram);
        List<InstagramUser> ditcherList = getDitcherList(followerSet, followingSet);
        return ditcherList;
    }

    private List<InstagramUser> getDitcherList(HashSet<InstagramUser> followers, HashSet<InstagramUser> followings) {
        List<InstagramUser> ditcherList = new ArrayList<>();
        for (InstagramUser followingUser : followings) {
            if (!followers.contains(followingUser)) {
                ditcherList.add(followingUser);
            }
        }
        return ditcherList;
    }

    private HashSet<InstagramUser> getFollowingSet(Instagram4j instagram) {
        HashSet<InstagramUser> followingSet = new HashSet<>();
        InstagramGetUserFollowersResult userFollowingRequest = getGetUserFollowersResult(instagram);
        for (InstagramUserSummary userSummary : userFollowingRequest.users) {
            followingSet.add(InstagramUser.from(userSummary));
        }
        return followingSet;
    }

    private HashSet<InstagramUser> getFollowerSet(Instagram4j instagram) {
        HashSet<InstagramUser> followerSet = new HashSet<>();
        InstagramGetUserFollowersResult userFollowersResult = getInstagramGetUserFollowersResult(instagram);
        for (InstagramUserSummary userSummary : userFollowersResult.users) {
            followerSet.add(InstagramUser.from(userSummary));
        }
        return followerSet;
    }

    @Nullable
    private InstagramGetUserFollowersResult getGetUserFollowersResult(Instagram4j instagram) {
        InstagramGetUserFollowersResult userFollowingRequest;
        try {
            userFollowingRequest = instagram.sendRequest(new InstagramGetUserFollowingRequest(instagram.getUserId()));
        } catch (Exception e) {
            return null;
        }
        return userFollowingRequest;
    }

    @Nullable
    private InstagramGetUserFollowersResult getInstagramGetUserFollowersResult(Instagram4j instagram) {
        InstagramGetUserFollowersResult userFollowersResult;
        try {
            userFollowersResult = instagram.sendRequest(new InstagramGetUserFollowersRequest(instagram.getUserId()));
        } catch (Exception e) {
            return null;
        }
        return userFollowersResult;
    }

    private Instagram4j getInstagram(HttpServletRequest request) {
        String authValue = request.getHeader("AUTH");
        return InstaAuthService.userInstaSessionMap.get(authValue);
    }
}
