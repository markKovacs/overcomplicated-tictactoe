package com.codecool.overcomplicated.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<String> board;
    private Player currentPlayer;
    private Player playerOne;
    private Player playerTwo;
    private String gameMode;

    public Game() {
        this.gameMode = "";
    }

    public Game(String gameMode) {
        this.board = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            board.add(null);
        }
        this.gameMode = gameMode;
    }

    public Move changeCell(int cellNumber) {

        // Validate cellNumber input from client
        if (cellNumber > 8 || cellNumber < 0 || board.get(cellNumber) != null) {
            return null;
        }

        // Make move and switch player
        board.set(cellNumber, currentPlayer.getSign());
        Move move = new Move(cellNumber, currentPlayer.getSign());
        currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;

        System.out.println(this);
        return move;
    }

    public String getCell(int index) {
        return this.board.get(index);
    }

    public List<String> getBoard() {
        return board;
    }

    public void setBoard(List<String> board) {
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (String s : this.board) {
            counter++;
            if (s == null) {
                sb.append("[ ]");
            } else {
                sb.append("[").append(s).append("]");
            }
            if (counter % 3 == 0) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public Player checkWinner() {
        String winnerSign = null;

        // Check rows
        for (int i = 0; i < 7; i = i + 3) {
            try {
                if (board.get(i).equals(board.get(i + 1)) && board.get(i + 1).equals(board.get(i + 2))) {
                    winnerSign = board.get(i);
                }
            } catch (NullPointerException e) {
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            try {
                if (board.get(i).equals(board.get(i + 3)) && board.get(i + 3).equals(board.get(i + 6))) {
                    winnerSign = board.get(i);
                }
            } catch (NullPointerException e) {
            }
        }
        // Check diagonals
        try {
            if (board.get(0).equals(board.get(4)) && board.get(4).equals(board.get(8))) {
                winnerSign = board.get(0);
            }
        } catch (NullPointerException e) {
        }
        try {
            if (board.get(2).equals(board.get(4)) && board.get(4).equals(board.get(6))) {
                winnerSign = board.get(2);
            }
        } catch (NullPointerException e) {
        }

        // No winner yet
        if (winnerSign == null) return null;

        return winnerSign.equals(playerOne.getSign()) ? playerOne : playerTwo;
    }

    public boolean checkIsGameFinished() {
        for (String s : board) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    public String toAPIString() {
        StringBuilder sb = new StringBuilder();
        for (String s : board) {
            if (s == null) {
                sb.append("-");
            } else {
                sb.append(s);
            }
        }

        sb.append("/").append(currentPlayer.getSign());
        return sb.toString();
    }

}
