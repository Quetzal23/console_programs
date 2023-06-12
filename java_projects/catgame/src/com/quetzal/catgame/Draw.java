package com.quetzal.catgame;

public class Draw {
    private final char TOP_LEFT;
    private final char TOP_RIGHT;
    private final char BOTTOM_LEFT;
    private final char BOTTOM_RIGHT;
    private final char HORIZONTAL;
    private final char VERTICAL;
    private final char CROSS;

    public Draw() {
        TOP_LEFT = '\u250C';        // ┌
        TOP_RIGHT = '\u2510';       // ┐
        BOTTOM_LEFT = '\u2514';     // └
        BOTTOM_RIGHT = '\u2518';    // ┘
        HORIZONTAL = '\u2500';      // ─
        VERTICAL = '\u2502';        // │
        CROSS = '\u253C';           // ┼
    }

    private char[][] drawBoard(char[][] board) {
        int rows = 5;
        int columns = 11;

        char[][] result = new char[rows][columns];

        for(int row = 0; row < rows; row++) {
            int position = 0;

            for(int column = 0; column < columns; column++) {
                if(row % 2 == 0) {
                    if(column % 2 == 0) {
                        result[row][column] = ' ';
                    } else {
                        if(column == 3 || column == 7) {
                            result[row][column] = VERTICAL;
                        } else {
                            result[row][column] = board[row / 2][position];
                            position++;
                        }
                    }
                } else {
                    if(column == 3 || column == 7) {
                        result[row][column] = CROSS;
                    } else {
                        result[row][column] = HORIZONTAL;
                    }
                }
            }
        }
        return result;
    }

    public void drawSquare(char[][] board) {
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
}
