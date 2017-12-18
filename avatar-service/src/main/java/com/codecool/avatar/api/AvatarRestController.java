package com.codecool.avatar.api;

import com.codecool.avatar.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvatarRestController {

    @Autowired
    private AvatarService adorableAvatarService;

    @GetMapping("/api/avatars/random")
    public String getRandomAvatarURI() {
        return adorableAvatarService.getRandomAvatarString();
    }

}
