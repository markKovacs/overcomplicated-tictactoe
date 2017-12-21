package com.codecool.overcomplicated.service;

import com.codecool.overcomplicated.model.Game;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class AiAPIService {

    public Integer getMove(Game game, String difficulty) {
        final String URI = "http://localhost:9060/api/ai/" + difficulty + "/" + game.toAPIString();
        RestTemplate restTemplate = new RestTemplate();
        Integer result;
        try {
            result = restTemplate.getForObject(URI, Integer.class);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            result = null;
        }
        return result;
    }

}
