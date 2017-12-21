package com.codecool.ai.api;

import com.codecool.ai.service.EasyAIAPIService;
import com.codecool.ai.service.HardAIAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiRestController {

    @Autowired
    private EasyAIAPIService easyAIAPIService;
    @Autowired
    private HardAIAPIService hardAIAPIService;

    @GetMapping("/api/ai/{difficulty}/{board}/{player}")
    public Integer getRecommendation(@PathVariable("board") String board,
                                     @PathVariable("player") String player,
                                     @PathVariable("difficulty") String difficulty) {

        Integer response = null;
        switch (difficulty) {
            case "easy": response = easyAIAPIService.getRecommendation(board, player); break;
            case "hard": response = hardAIAPIService.getRecommendation(board, player); break;
        }

        return response;
    }

}
