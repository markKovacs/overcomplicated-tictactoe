package com.codecool.funfact.api;

import com.codecool.funfact.service.FunFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunFactRestController {

    @Autowired
    private FunFactService chuckNorrisFunFactService;

    @GetMapping("/api/funfacts/random")
    public String getRandomAvatarURI() {
        return chuckNorrisFunFactService.getRandomFunFact();
    }

}
