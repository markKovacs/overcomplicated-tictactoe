package com.codecool.overcomplicated.controller;

import com.codecool.overcomplicated.model.Game;
import com.codecool.overcomplicated.model.GameDto;
import com.codecool.overcomplicated.model.Move;
import com.codecool.overcomplicated.model.Player;
import com.codecool.overcomplicated.service.AiAPIService;
import com.codecool.overcomplicated.service.AvatarAPIService;
import com.codecool.overcomplicated.service.ComicsAPIService;
import com.codecool.overcomplicated.service.FunFactAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"game"})
public class GameController {

    @Autowired
    private AvatarAPIService avatarAPIService;
    @Autowired
    private ComicsAPIService comicsAPIService;
    @Autowired
    private FunFactAPIService funFactAPIService;
    @Autowired
    private AiAPIService aiAPIService;

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView onIllegalStateException(Exception e) {
        System.out.println(e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", e.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ModelAttribute("game")
    public Game getDefaultGame() {
        return new Game();
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
            case "one":
                game.getPlayerOne().setUserName(userName);
                break;
            case "two":
                game.getPlayerTwo().setUserName(userName);
                break;
        }
        return "redirect:/game?reqMode=" + game.getGameMode();
    }

    @GetMapping(value = "/game")
    public String gameView(@RequestParam(value = "reqMode", required = false) String reqMode,
                           @ModelAttribute("game") Game game, Model model) {

        if (reqMode == null) {
            throw new IllegalStateException("Game mode not specified in query string");
        }

        String currMode = game.getGameMode();
        String gameMode = null;

        // Handles if user changes URL query string
        if (reqMode.equals("easy") && !currMode.equals("easy")) {
            gameMode = "easy";
        } else if (reqMode.equals("hard") && !currMode.equals("hard")) {
            gameMode = "hard";
        } else if (reqMode.equals("multi") && !currMode.equals("multi")) {
            gameMode = "multi";
        }

        // Create new game only if user changed query string manually
        if (gameMode != null) {
            model.addAttribute("game", getGame(gameMode));
        }
        model.addAttribute("funfact", funFactAPIService.getFunFact());

        return "game";
    }

    @GetMapping(value = "/")
    public String welcomeView(SessionStatus sessionStatus) {
        // Clears session and resets game object
        sessionStatus.setComplete();
        return "welcome";
    }

    @PostMapping(value = "/api/change-board")
    public @ResponseBody
    GameDto gameMove(@RequestParam("cellNumber") int moveOfPlayer,
                     @ModelAttribute("game") Game game) {

        Move playerMove = null;
        Move aiMove = null;

        Move move = game.changeCell(moveOfPlayer);
        if (move != null) {
            Integer moveOfAI = null;
            if (game.getGameMode().equals("easy")) {
                moveOfAI = aiAPIService.getMove(game, "easy");
            } else if (game.getGameMode().equals("hard")) {
                moveOfAI = aiAPIService.getMove(game, "hard");
            }
            if (moveOfAI != null) {
                game.changeCell(moveOfAI);
                aiMove = new Move(moveOfAI, "X");
            }
            playerMove = move;
        }

        Player winner = game.checkWinner();
        boolean isDraw = false;
        if (winner == null) {
            isDraw = game.checkIsGameFinished();
        }

        System.out.println("Winner: " + winner);
        System.out.println("Is Draw: " + isDraw);

        return new GameDto(playerMove, aiMove, winner, isDraw);
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

}
