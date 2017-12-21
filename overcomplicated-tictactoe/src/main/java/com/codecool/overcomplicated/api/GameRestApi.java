package com.codecool.overcomplicated.api;

import com.codecool.overcomplicated.model.Game;
import com.codecool.overcomplicated.model.Move;
import com.codecool.overcomplicated.model.Player;
import com.codecool.overcomplicated.service.AiService;
import com.codecool.overcomplicated.utils.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes({"game"})
public class GameRestApi {

    @Autowired
    private AiService dumbAIAPIService;

    @PostMapping(value = "/api/change-board")
    public GameDto gameMove(@RequestParam("cellNumber") int moveOfPlayer,
                            @ModelAttribute("game") Game game) {

        Move playerMove = null;
        Move aiMove = null;

        Move move = game.changeCell(moveOfPlayer);
        if (move != null) {
            if (game.getPlayerMode().equals("one")) {
                // single player, valid move
                Integer moveOfAI = dumbAIAPIService.getMove(game);
                if (moveOfAI != null) {
                    game.changeCell(moveOfAI);
                    aiMove = new Move(moveOfAI, "X");
                }
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
