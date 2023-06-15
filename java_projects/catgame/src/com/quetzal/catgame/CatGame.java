package com.quetzal.catgame;

import java.util.Scanner;

class GameBoard {
    private char[][] board = new char[][] {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    private char TOP_LEFT = '\u250C';       // ┌
    private char TOP_RIGHT = '\u2510';      // ┐
    private char BOTTOM_LEFT = '\u2514';    // └
    private char BOTTOM_RIGHT = '\u2518';   // ┘
    private char HORIZONTAL = '\u2500';     // ─
    private char VERTICAL = '\u2502';       // │
    private char CROSS = '\u253C';          // ┼

    private void drawSquare(char[][] board) {
        char[][] drawBoard = drawBoard(board);

        int rows = (drawBoard.length) + 2;
        int columns = (drawBoard[0].length) + 2;

        for(int height = 0; height <= rows; height++) {
            for(int width = 0; width <= columns; width++) {
                if(height == 0) {
                    if(width % 2 == 0) {
                        System.out.print(" ");
                    } else {
                        if(width == 3 || width == 7 || width == 11) {
                            System.out.print(width / 4);
                        } else {
                            System.out.print(' ');
                        }
                    }
                } else if(height == 1) {
                    if(width == 0) {
                        System.out.print(' ');
                    } else if(width == 1) {
                        System.out.print(TOP_LEFT);
                    } else if(width == columns) {
                        System.out.print(TOP_RIGHT);
                    } else {
                        System.out.print(HORIZONTAL);
                    }
                } else if(height >= rows) {
                    if(width == 0) {
                        System.out.print(' ');
                    } else if(width == 1) {
                        System.out.print(BOTTOM_LEFT);
                    } else if(width == columns) {
                        System.out.print(BOTTOM_RIGHT);
                    } else {
                        System.out.print(HORIZONTAL);
                    }
                } else {
                    if(width == 0) {
                        if(height % 2 == 0) {
                            System.out.print(height / 2 - 1);
                        } else {
                            System.out.print(' ');
                        }
                    } else if(width == 1) {
                        System.out.print(VERTICAL);
                    } else if(width == columns) {
                        System.out.print(VERTICAL);
                    } else {
                        System.out.print(drawBoard[height - 2][width - 2]);
                    }
                }
            }
            System.out.println();
        }
    }

    private char[][] drawBoard(char[][] board) {
        int rows = 5;
        int columns = 11;

        char[][] result = new char[rows][columns];

        for (int row = 0; row < rows; row++) {
            int position = 0;

            for (int col = 0; col < columns; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        result[row][col] = ' ';
                    } else {
                        if (col == 3 || col == 7) {
                            result[row][col] = VERTICAL;
                        } else {
                            result[row][col] = board[row / 2][position];
                            position += 1;
                        }
                    }
                } else {
                    if (col == 3 || col == 7) {
                        result[row][col] = CROSS;
                    } else {
                        result[row][col] = HORIZONTAL;
                    }
                }
            }
        }

        return result;
    }

    public GameBoard() {
        resetBoard();
    }

    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    public void displayBoard() {
        drawSquare(board);
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (board[row][col] == ' ') {
            board[row][col] = symbol;
            return true;
        } else {
            return false;
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(char symbol) {
        //  Verificar diagonal principal
        boolean isWin = true;
        for (int row = 0; row < 3; row++) {
            if (board[row][row] != symbol) {
                isWin = false;
                break;
            }
        }
        if (isWin) {
            return true;    // Hay un ganador en la diagonal principal
        }

        // Verificar diagonal inversa
        isWin = true;
        for (int row = 0; row < 3; row++) {
            if (board[row][2 - row] != symbol) {
                isWin = false;
                break;
            }
        }
        if (isWin) {
            return true;    // Hay un ganador en la diagonal inversa
        }

        // Verificar filas
        for (int row = 0; row < 3; row++) {
            isWin = true;
            for (int col = 0; col < 3; col++) {
                if (board[row][col] != symbol) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                return true;    // Hay un ganador en la fila actual
            }
        }

        for (int col = 0; col < 3; col++) {
            isWin = true;
            for (int row = 0; row < 3; row++) {
                if (board[row][col] != symbol) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                return true;    // Hay un ganador en la columna actual
            }
        }

        return false;  // No hay ganador
    }
}

class Player {
    private String name;
    private char symbol;

    public Player(String playerName, char playerSymbol) {
        name = playerName;
        symbol = playerSymbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

class GameManager {
    private Player player1;
    private Player player2;
    private GameBoard gameBoard;
    private int currentTurn;
    private Scanner scanner;

    public GameManager(String player1Name, char player1Symbol, String player2Name, char player2Symbol) {
        player1 = new Player(player1Name, player1Symbol);
        player2 = new Player(player2Name, player2Symbol);

        gameBoard = new GameBoard();
        scanner = new Scanner(System.in);

        currentTurn = 1;
    }

    public void playGame() {
        boolean gameOver = false;

        while (!gameOver) {
            gameBoard.displayBoard();

            Player currentPlayer = (currentTurn % 2 == 1) ? player1 : player2;

            int row, column;

            System.out.print("Turno de: " + currentPlayer.getName() + "\nIngresa la Fila: ");
            row = scanner.nextInt();
            System.out.print("Ingresa la Columna: ");
            column = scanner.nextInt();

            if (gameBoard.makeMove(row, column, currentPlayer.getSymbol())) {
                if (gameBoard.checkWin(currentPlayer.getSymbol())) {
                    System.out.println("¡Felicidades, " + currentPlayer.getName() + "! ¡Has ganado!");
                    gameOver = true;
                    break;
                } else if (gameBoard.isBoardFull()) {
                    System.out.println("El juego ha terminado en empate.");
                    gameOver = true;
                    break;
                } else {
                    currentTurn++;
                }
            } else {
                System.out.println("Movimiento inválido. Intenta nuevamente.");
            }
        }

        gameBoard.displayBoard();
    }
}

class CatGame {
    public static void main(String[] args) {
        char player1Symbol = 'X';
        char player2Symbol = 'O';

        String player1Name;
        String player2Name;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Jugador 1, ingresa tu nombre: ");
        player1Name = scanner.nextLine();
        System.out.print("Jugador 2, ingresa tu nombre: ");
        player2Name = scanner.nextLine();

        GameManager gameManager = new GameManager(player1Name, player1Symbol, player2Name, player2Symbol);
        gameManager.playGame();
    }
}
