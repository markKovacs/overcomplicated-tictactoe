package com.codecool.overcomplicated.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class AvatarAPIService implements AvatarService {

    @Override
    public String getAvatarURI() {
        final String URI = "http://localhost:9000/api/avatars/random";
        RestTemplate restTemplate = new RestTemplate();
        String result;
        try {
            result = restTemplate.getForObject(URI, String.class);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            result = "/images/default_avatar.png";
        }
        return result;
    }

}
