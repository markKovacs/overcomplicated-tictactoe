package com.codecool.overcomplicated.model;

public class GameDto {
    private Move playerMove;
    private Move aiMove;
    private Player winner;
    private boolean isDraw;

    public GameDto(Move playerMove, Move aiMove, Player winner, boolean isDraw) {
        this.playerMove = playerMove;
        this.aiMove = aiMove;
        this.winner = winner;
        this.isDraw = isDraw;
    }

    public Move getPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(Move playerMove) {
        this.playerMove = playerMove;
    }

    public Move getAiMove() {
        return aiMove;
    }

    public void setAiMove(Move aiMove) {
        this.aiMove = aiMove;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
