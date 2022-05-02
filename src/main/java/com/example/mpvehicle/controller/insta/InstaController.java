package com.example.mpvehicle.controller.insta;

import com.example.mpvehicle.entities.InstaAuthBody;
import com.example.mpvehicle.entities.InstagramUser;
import com.example.mpvehicle.service.insta.InstaAuthService;
import com.example.mpvehicle.service.insta.InstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController("/insta")
@CrossOrigin(origins = "http://localhost:4200")
public class InstaController {
    @Autowired
    InstaService instaService;

    @Autowired
    InstaAuthService instaAuthService;

    @PostMapping("/auth") // takes the parameter in which url it is used to
    public String auth(@RequestBody InstaAuthBody instaAuthBody) throws IOException {
        return instaAuthService.authentication(instaAuthBody);
    }

    @GetMapping("/findFollowDitchersList") // takes the parameter in which url it is used to
    public List<InstagramUser> findFollowDitchersList(HttpServletRequest request) throws IOException {
        return instaService.findFollowDitchersList(request);
    }
}