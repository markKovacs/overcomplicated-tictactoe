package com.codecool.comics.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class XkcdComicsAPIService implements ComicsService {

    @Override
    public String getRandomComicsString() {
        Random rnd = new Random();
        final String URI = "https://xkcd.com/" + (1001 + rnd.nextInt(928)) + "/info.0.json";

        RestTemplate restTemplate = new RestTemplate();
        String result;
        try {
            result = restTemplate.getForObject(URI, String.class);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return "/images/default_avatar.png";
        }

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

        return jsonObject.get("img").getAsString();
    }

}
