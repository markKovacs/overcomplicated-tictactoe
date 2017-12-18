package com.codecool.avatar.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AdorableAvatarAPIService implements AvatarService {

    @Override
    public String getRandomAvatarString() {
        Random rnd = new Random();
        return "https://api.adorable.io/avatars/285/" + rnd.nextInt(999);
    }

}
