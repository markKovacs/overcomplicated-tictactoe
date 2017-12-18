package com.codecool.comics.api;

import com.codecool.comics.service.ComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComicsRestController {

    @Autowired
    private ComicsService xkcdComicsAPIService;

    @GetMapping("/api/comics/random")
    public String getRandomAvatarURI() {
        return xkcdComicsAPIService.getRandomComicsString();
    }

}
