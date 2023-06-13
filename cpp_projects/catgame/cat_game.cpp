#include <iostream>
#include <string>

class GameLogic {
private:
    char board[3][3] = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static char player1Symbol;
    static char player2Symbol;

public:
    char (*getBoard())[3] {
        return board;
    }

    static void choosePlayerSymbol() {
        std::string input;
        char symbol;

        std::cout << "Jugador 1.\nElige tu ficha:\n";
        std::cout << "- X\n";
        std::cout << "- O\n";

        std::cin >> input;
        symbol = std::toupper(input[0]);
        if (symbol == 'X' || symbol == 'O') {
            player1Symbol = symbol;
            player2Symbol = (player1Symbol == 'O') ? 'X' : 'O';
        } else {
            player1Symbol = 'O';
            player2Symbol = 'X';
        }

        std::cout << "Jugador 1: " << player1Symbol << "\n";
        std::cout << "Jugador 2: " << player2Symbol << "\n";

        std::cout << std::endl;
    }
};

char GameLogic::player1Symbol = ' ';
char GameLogic::player2Symbol = ' ';

class Draw {
private:
    static const char* TOP_LEFT;
    static const char* TOP_RIGHT;
    static const char* BOTTOM_LEFT;
    static const char* BOTTOM_RIGHT;
    static const char* HORIZONTAL;
    static const char* VERTICAL;
    static const char* CROSS;

    static char (*drawBoard(char board[3][3], int rows, int columns))[11] {
        char (*result)[11] = new char[rows][11];

        for (int row = 0; row < rows; row++) {
            int position = 0;

            for (int column = 0; column < columns; column++) {
                if (row % 2 == 0) {
                    if (column % 2 == 0) {
                        result[row][column] = ' ';
                    } else {
                        if (column == 3 || column == 7) {
                            result[row][column] = *VERTICAL;
                        } else {
                            result[row][column] = board[row / 2][position];
                            position++;
                        }
                    }
                } else {
                    if (column == 3 || column == 7) {
                        result[row][column] = *CROSS;
                    } else {
                        result[row][column] = *HORIZONTAL;
                    }
                }
            }
        }

        return result;
    }

public:
    static void drawSquare(char board[3][3]) {
        int boardRows = 5;
        int boardColumns = 11;

        char (*draw_board)[11] = drawBoard(board, boardRows, boardColumns);

        int rows = boardRows + 2;
        int columns = boardColumns + 2;

        for(int height = 0; height <= rows; height++) {
            for(int width = 0; width <= columns; width++) {
                if(height == 0) {
                    if(width % 2 == 0) {
                        std::cout << ' ';
                    } else {
                        if(width == 3 || width == 7 || width == 11) {
                            std::cout << width / 4;
                        } else {
                            std::cout << ' ';
                        }
                    }
                } else if(height == 1) {
                    if(width == 0) {
                        std::cout << ' ';
                    } else if(width == 1) {
                        std::cout << TOP_LEFT;
                    } else if(width == columns) {
                        std::cout << TOP_RIGHT;
                    } else {
                        std::cout << HORIZONTAL;
                    }
                } else if (height >= rows) {
                    if(width == 0) {
                        std::cout << ' ';
                    } else if(width == 1) {
                        std::cout << BOTTOM_LEFT;
                    } else if(width == columns) {
                        std::cout << BOTTOM_RIGHT;
                    } else {
                        std::cout << HORIZONTAL;
                    }
                } else {
                    if(width == 0) {
                        if(height % 2 == 0) {
                            std::cout << height / 2 - 1;
                        } else {
                            std::cout << ' ';
                        }
                    } else if(width == 1) {
                        std::cout << VERTICAL;
                    } else if(width == columns) {
                        std::cout << VERTICAL;
                    } else {
                        std::cout << draw_board[height - 2][width - 2];
                    }
                }
            }
            std::cout << std::endl;
        }

        delete[] draw_board;
    }
};

const char* Draw::TOP_LEFT = "o";//u8"\u250C";      // ┌
const char* Draw::TOP_RIGHT = "o";//u8"\u2510";     // ┐
const char* Draw::BOTTOM_LEFT = "o";//u8"\u2514";   // └
const char* Draw::BOTTOM_RIGHT = "o";//u8"\u2518";  // ┘
const char* Draw::HORIZONTAL = "-";//u8"\u2500";    // ─
const char* Draw::VERTICAL = "|";//u8"\u2502";      // │
const char* Draw::CROSS = "o";//u8"\u253C";         // ┼

int main() {
    GameLogic gameLogic;
    Draw draw;

    gameLogic.choosePlayerSymbol();

    char (*board)[3] = gameLogic.getBoard();
    draw.drawSquare(board);

    return 0;
}
