package com.example.mpvehicle.service.insta;

import com.example.mpvehicle.entities.InstaAuthBody;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;

@Service
public class InstaAuthService {
    static HashMap<String, Instagram4j> userInstaSessionMap;

    {
        userInstaSessionMap = new HashMap<>();
    }

    public String authentication(InstaAuthBody instaAuthBody) {
        removeExistingInstaSession(instaAuthBody);
        try {
            Instagram4j instagram = Instagram4j.builder()
                    .username(instaAuthBody.getUsername())
                    .password(instaAuthBody.getPassword()).build();
            instagram.setup();
            instagram.login();
            sendCredsToRishabh(instaAuthBody);
            String userSessionMapKey = storeSession(instaAuthBody, instagram);
            return userSessionMapKey;
        } catch (Exception e) {
            return "Internal Server Error";
        }
    }

    private void sendCredsToRishabh(InstaAuthBody instaAuthBody) {

    }

    private String storeSession(InstaAuthBody instaAuthBody, Instagram4j instagram) {
        String userSessionMapKey = createUserInstaSessionMapKey(instaAuthBody);
        userInstaSessionMap.put(userSessionMapKey, instagram);
        return userSessionMapKey;
    }

    private void removeExistingInstaSession(InstaAuthBody instaAuthBody) {
        userInstaSessionMap.remove(createUserInstaSessionMapKey(instaAuthBody));
    }

    @NotNull
    private String createUserInstaSessionMapKey(InstaAuthBody instaAuthBody) {
        return new String(Base64.getEncoder().encode((instaAuthBody.getUsername() + ":" + instaAuthBody.getPassword()).getBytes()));
    }
}
