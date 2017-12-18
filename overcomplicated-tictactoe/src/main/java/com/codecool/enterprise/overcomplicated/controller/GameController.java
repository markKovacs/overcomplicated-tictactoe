package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.service.AvatarAPIService;
import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TicTacToeGame;
import com.codecool.enterprise.overcomplicated.service.ComicAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"player", "game", "avatar_uri"})
public class GameController {

    @Autowired
    private AvatarAPIService avatarAPIService;

    @Autowired
    private ComicAPIService comicAPIService;

    @ModelAttribute("player")
    public Player getPlayer() {
        return new Player();
    }

    @ModelAttribute("game")
    public TicTacToeGame getGame() {
        return new TicTacToeGame();
    }

    @ModelAttribute("avatar_uri")
    public String getAvatarURI() {
        return avatarAPIService.getAvatarURI();
    }

    @ModelAttribute
    public void refreshAvatarAttribute(Model model) {
        if (model.asMap().get("avatar_uri").equals("/images/default_avatar.png")) {
            model.addAttribute("avatar_uri", avatarAPIService.getAvatarURI());
        }
    }

    @ModelAttribute("comic_uri")
    public String getComicURI() {
        return comicAPIService.getComicURI();
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value="/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) {
        model.addAttribute("funfact", "&quot;Chuck Norris knows the last digit of pi.&quot;");
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move) {
        System.out.println("Player moved " + move);
        return "redirect:/game";
    }
}
