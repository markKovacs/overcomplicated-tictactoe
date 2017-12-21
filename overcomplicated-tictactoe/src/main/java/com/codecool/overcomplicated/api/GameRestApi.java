package com.codecool.overcomplicated.api;

import com.codecool.overcomplicated.model.Game;
import com.codecool.overcomplicated.model.Move;
import com.codecool.overcomplicated.model.Player;
import com.codecool.overcomplicated.service.AiAPIService;
import com.codecool.overcomplicated.utils.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes({"game"})
public class GameRestApi {

    @Autowired
    private AiAPIService aiAPIService;

    @PostMapping(value = "/api/change-board")
    public GameDto gameMove(@RequestParam("cellNumber") int moveOfPlayer,
                            @ModelAttribute("game") Game game) {

        Move playerMove = null;
        Move aiMove = null;

        Move move = game.changeCell(moveOfPlayer);
        if (move != null) {
            Integer moveOfAI = null;
            if (game.getGameMode().equals("easy")) {
                System.out.println("Easy computer making move.");
                moveOfAI = aiAPIService.getMove(game, "easy");
            } else if (game.getGameMode().equals("hard")) {
                System.out.println("Hard computer making move.");
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

}
