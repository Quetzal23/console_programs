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
        TOP_LEFT = '\u250C';  // ┌
        TOP_RIGHT = '\u2510';  // ┐
        BOTTOM_LEFT = '\u2514';  // └
        BOTTOM_RIGHT = '\u2518';  // ┘
        HORIZONTAL = '\u2500';  // ─
        VERTICAL = '\u2502';  // │
        CROSS = '\u253C';  // ┼
    }

    private char[][] drawBoard(char[][] board) {
        int rows = 5;
        int columns = 11;

        char[][] result = new char[rows][columns];

        for(int row=0; row<rows; row++) {
            for(int column=0; column<columns; column++) {
                // System.out.print(column);
                if(row % 2 == 0) {
                    // System.out.print(column);
                    if(column == 3 || column == 7) {
                        System.out.print(VERTICAL);
                    } else {
                        System.out.print("0");
                    }
                } else {
                    if(column == 3 || column == 7) {
                        System.out.print(CROSS);
                    } else {
                        System.out.print(HORIZONTAL);
                    }
                }
            }
            System.out.println("");
        }

        return result;
    }

    public void drawSquare() {
        char[][] board = {
            {'o', 'o', 'o'},
            {'o', 'o', 'o'},
            {'o', 'o', 'o'}
        };

        Object drawBoard = drawBoard(board);
        // System.out.print(drawBoard);

        int rows = 5 + 3;
        int columns = 11 + 3;

        for(int height=0; height<=rows; height++) {
            for(int width=0; width<=columns; width++) {
                if(height == 0) {
                    if(width % 2 == 0) {
                        System.out.print("0");
                    } else {
                        System.out.print(" ");
                    }
                } else if(height == 1) {
                    if(width == 0) {
                        System.out.print(" ");
                    } else if(width == 1) {
                        System.out.print(TOP_LEFT);
                    } else if(width == columns) {
                        System.out.print(TOP_RIGHT);
                    } else {
                        System.out.print(HORIZONTAL);
                    }
                } else if(height >= rows) {
                    if(width == 0) {
                        System.out.print(" ");
                    } else if(width == 1) {
                        System.out.print(BOTTOM_LEFT);
                    } else if(width == columns) {
                        System.out.print(BOTTOM_RIGHT);
                    } else {
                        System.out.print(HORIZONTAL);
                    }
                } else {
                    if(width == 0) {
                        System.out.print("0");
                    } else if(width == 1) {
                        System.out.print(VERTICAL);
                    } else if(width == columns) {
                        System.out.print(VERTICAL);
                    } else {
                        if(width % 2 == 0) {
                            System.out.print("0");
                        } else {
                            System.out.print(" ");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}
