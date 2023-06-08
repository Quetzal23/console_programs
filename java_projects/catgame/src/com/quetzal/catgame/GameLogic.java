package com.quetzal.catgame;

public class GameLogic {
    private char[][] board;

    public GameLogic() {
        board = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        char player1 = 'X';
        char player2 = 'O';
    }

    public char[][] getBoard() {
        return board;
    }

    public void makeMove(int row, int col) {}

    public boolean isGameOver() {
        return false;
    }
}
