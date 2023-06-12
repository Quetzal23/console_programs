package com.quetzal.catgame;

public class CatGame {

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        Draw draw = new Draw();

        gameLogic.choosePlayerSymbol();

        char[][] board = gameLogic.getBoard();
        draw.drawSquare(board);
    }
}
