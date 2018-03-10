package com.codecool.overcomplicated.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FunFactAPIService {

    public String getFunFact() {

        final String URI = "http://localhost:9040/api/funfacts/random";
        RestTemplate restTemplate = new RestTemplate();
        String result;

        try {
            result = restTemplate.getForObject(URI, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            result = "&quot;Chuck Norris knows the last digit of pi.&quot;";
        }

        return result;
    }

}
