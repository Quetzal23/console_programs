package com.quetzal.catgame;

import java.util.Scanner;

public class GameLogic {
    private char[][] board;
    private char player1Symbol;
    private char player2Symbol;

    public GameLogic() {
        board = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        player1Symbol = ' ';
        player2Symbol = ' ';
    }

    public char[][] getBoard() {
        return board;
    }

    public void makeMove(int row, int col) {}

    public boolean isGameOver() {
        return true;
    }

    public void choosePlayerSymbol() {
        System.out.println("Jugador 1.\nElige tu ficha:");
        System.out.println("- X");
        System.out.println("- O");

        try (Scanner scanner = new Scanner(System.in)) {
            String symbol = scanner.nextLine().toUpperCase();
            if(symbol.equals("O") || symbol.equals("X")) {
                player1Symbol = symbol.charAt(0);
                player2Symbol = (player1Symbol == 'O') ? 'X' : 'O';
            } else {
                System.out.println("Error, ficha no valida.\nSe seleccionó al Jugador 1 la ficha por defecto: 'O' ");
                player1Symbol = 'O';
                player2Symbol = 'X';
            }

            System.out.println("Jugador 1: " + player1Symbol);
            System.out.println("Jugador 2: " + player2Symbol);
        }

        System.out.println("");
    }
}
