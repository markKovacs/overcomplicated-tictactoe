package com.codecool.ai.api;

import com.codecool.ai.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiRestController {

    @Autowired
    private AiService dumbAIAPIService;

    @GetMapping("/api/ai/{board}/{player}")
    public Integer getRecommendation(@PathVariable("board") String board,
                                     @PathVariable("player") String player) {

        return dumbAIAPIService.getRecommendation(board, player);
    }

}
