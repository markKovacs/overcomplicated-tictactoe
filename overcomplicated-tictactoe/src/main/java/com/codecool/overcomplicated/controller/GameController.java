package com.codecool.overcomplicated.controller;

import com.codecool.overcomplicated.model.Game;
import com.codecool.overcomplicated.model.Player;
import com.codecool.overcomplicated.service.AvatarAPIService;
import com.codecool.overcomplicated.service.ComicsAPIService;
import com.codecool.overcomplicated.service.FunFactAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"game"})
public class GameController {

    @Autowired
    private AvatarAPIService avatarAPIService;
    @Autowired
    private ComicsAPIService comicsAPIService;
    @Autowired
    private FunFactAPIService funFactAPIService;

    @ModelAttribute("game")
    public Game getGame() {
        Player playerOne = Player.createPlayerOne(avatarAPIService.getAvatarURI());
        Game game = new Game("one");
        game.setPlayerOne(playerOne);
        game.setCurrentPlayer(playerOne);

        if (game.isTwoPlayerMode()) {
            game.setPlayerTwo(Player.createPlayerTwo(avatarAPIService.getAvatarURI()));
        } else {
            game.setPlayerTwo(Player.createAiPlayer());
        }

        return game;
    }

    @ModelAttribute("comic_uri")
    public String getComicURI() {
        return comicsAPIService.getComicURI();
    }

    @GetMapping(value = "/")
    public String welcomeView(SessionStatus sessionStatus, @SessionAttribute("game") Game game) {
        sessionStatus.setComplete();
        return "welcome";
    }

    @PostMapping(value = "/changeplayeronename")
    public String changePlayerOneName(@RequestParam("userName") String userName, @ModelAttribute("game") Game game) {
        game.getPlayerOne().setUserName(userName);
        return "redirect:/game";
    }

    @PostMapping(value = "/changeplayertwoname")
    public String changePlayerTwoName(@RequestParam("userName") String userName, @ModelAttribute("game") Game game) {
        game.getPlayerTwo().setUserName(userName);
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(Model model) {
        model.addAttribute("funfact", funFactAPIService.getFunFact());
        return "game";
    }

    @GetMapping(value = "/new-game")
    public String newGame(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
