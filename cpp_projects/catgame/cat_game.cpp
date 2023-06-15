#include <iostream>
#include <array>

class GameBoard {
    private:
    char board[3][3];

    static constexpr char TOP_LEFT = 'o';
    static constexpr char TOP_RIGHT = 'o';
    static constexpr char BOTTOM_LEFT = 'o';
    static constexpr char BOTTOM_RIGHT = 'o';
    static constexpr char HORIZONTAL = '-';
    static constexpr char VERTICAL = '|';
    static constexpr char CROSS = '+';

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
                            result[row][column] = VERTICAL;
                        } else {
                            result[row][column] = board[row / 2][position];
                            position++;
                        }
                    }
                } else {
                    if (column == 3 || column == 7) {
                        result[row][column] = CROSS;
                    } else {
                        result[row][column] = HORIZONTAL;
                    }
                }
            }
        }

        return result;
    }

    public:
    GameBoard() {
        // Iniciar tablero vacio = ' '
        resetBoard();
    }

    void resetBoard() {
        // Reiniciar el tablero a vacio
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    void displayBoard() {
        drawSquare(board);
    }

    bool makeMove(int row, int col, char symbol) {
        // Lógica para validar el movimiento y actualizar el tablero
        if (board[row][col] == ' ') {
            board[row][col] = symbol;
            return true;
        } else {
            return false;
        }
    }

    bool isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    bool checkWin(char symbol) {
        //  Verificar diagonal principal
        bool isWin = true;
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
};

class Player {
    private:
    std::string name;
    char symbol;

    public:
    Player(const std::string playerName, char playerSymbol): name(playerName), symbol(playerSymbol) {}

    const std::string& getName() const {
        return name;
    }

    char getSymbol() const {
        return symbol;
    }
};

class GameManager {
    private:
    Player player1;
    Player player2;
    GameBoard gameBoard;
    int currentTurn;

    public:
    GameManager(const std::string& player1Name, char player1Symbol, const std::string& player2Name, char player2Symbol)
        : player1(player1Name, player1Symbol), player2(player2Name, player2Symbol), currentTurn(1) {}

    void playGame() {
        bool gameOver = false;

        while(!gameOver) {
            gameBoard.displayBoard();

            Player* currentPlayer = (currentTurn % 2 == 1) ? &player1 : &player2;

            int row, column;

            std::cout << "Turno de: " << currentPlayer->getName() << "\nIngresa la Fila: ";
            std::cin >> row;
            std::cout << "Ingresa la Columna: ";
            std::cin >> column;

            // Realizar el movimiento en el tablero
            if (gameBoard.makeMove(row, column, currentPlayer->getSymbol())) {
                if (gameBoard.checkWin(currentPlayer->getSymbol())) {
                    std::cout << "¡Felicidades, " << currentPlayer->getName() << "! ¡Has ganado!" << std::endl;
                    gameOver = true;
                    break;
                } else if (gameBoard.isBoardFull()) {
                    std::cout << "El juego ha terminado en empate." << std::endl;
                    gameOver = true;
                    break;
                } else {
                    currentTurn++;
                }
            } else {
                std::cout << "Movimiento inválido. Intenta nuevamente." << std::endl;
            }
        }

        gameBoard.displayBoard();
    }

};

int main() {
    std::string player1Name, player2Name;
    char player1Symbol = 'X', player2Symbol = 'O';

    // Obtener el nombre del primer jugador
    std::cout << "Jugador 1, ingresa tu nombre: ";
    std::cin >> player1Name;

    // Obtener el nombre del segundo jugador
    std::cout << "Jugador 2, ingresa tu nombre: ";
    std::cin >> player2Name;

    // Crear una instancia de GameManager y jugar el juego
    GameManager gameManager(player1Name, player1Symbol, player2Name, player2Symbol);
    gameManager.playGame();

    return 0;
}
