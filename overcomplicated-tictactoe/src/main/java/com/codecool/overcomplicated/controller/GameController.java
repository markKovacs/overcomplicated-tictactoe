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
    public Game getDefaultGame() {
        return new Game();
    }

    private Game getGame(String mode) {
        Player playerOne = Player.createPlayerOne(avatarAPIService.getAvatarURI());
        Game game = new Game(mode);
        game.setPlayerOne(playerOne);
        game.setCurrentPlayer(playerOne);

        if (mode.equals("multi")) {
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

    @PostMapping(value = "/change-name/{player}")
    public String changePlayerName(@RequestParam("userName") String userName,
                                   @ModelAttribute("game") Game game,
                                   @PathVariable("player") String player) {

        switch (player) {
            case "one": game.getPlayerOne().setUserName(userName); break;
            case "two": game.getPlayerTwo().setUserName(userName); break;
        }
        return "redirect:/game?reqMode=" + game.getGameMode();
    }

    @GetMapping(value = "/game")
    public String gameView(Model model, @RequestParam("reqMode") String reqMode, @ModelAttribute("game") Game game) {

        String currMode = game.getGameMode();
        String gameMode = null;

        // Compares requested and currently selected game modes
        if (reqMode.equals("easy") && !currMode.equals("easy")) {
            gameMode = "easy";
        } else if (reqMode.equals("hard") && !currMode.equals("hard")) {
            gameMode = "hard";
        } else if (reqMode.equals("multi") && !currMode.equals("multi")) {
            gameMode = "multi";
        }

        model.addAttribute("game", getGame(gameMode));
        model.addAttribute("funfact", funFactAPIService.getFunFact());

        return "game";
    }

    @GetMapping(value = "/")
    public String welcomeView(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "welcome";
    }

}
