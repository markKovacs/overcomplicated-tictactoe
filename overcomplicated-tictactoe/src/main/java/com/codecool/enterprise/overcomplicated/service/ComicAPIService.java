package com.codecool.enterprise.overcomplicated.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ComicAPIService {

    public String getComicURI() {
        final String URI = "http://localhost:9020/api/comics/random";
        RestTemplate restTemplate = new RestTemplate();
        String result;
        try {
            result = restTemplate.getForObject(URI, String.class);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            result = "/images/default_comic.png";
        }
        return result;    }
}
