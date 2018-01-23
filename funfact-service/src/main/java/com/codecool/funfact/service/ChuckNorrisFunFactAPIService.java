package com.codecool.funfact.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChuckNorrisFunFactAPIService implements FunFactService {

    @Override
    public String getRandomFunFact() {

        final String URI = "https://api.chucknorris.io/jokes/random";
        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return "&quot;Chuck Norris knows the last digit of pi.&quot;";
        }

        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        return "&quot;" + jsonObject.get("value").getAsString() + "&quot;";
    }

}
