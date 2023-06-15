class GameBoard:
    _board = [
        [' ', ' ', ' '],
        [' ', ' ', ' '],
        [' ', ' ', ' ']
    ]

    _TOP_LEFT = '\u250C'        # ┌
    _TOP_RIGHT = '\u2510'       # ┐
    _BOTTOM_LEFT = '\u2514'     # └
    _BOTTOM_RIGHT = '\u2518'    # ┘
    _HORIZONTAL = '\u2500'      # ─
    _VERTICAL = '\u2502'        # │
    _CROSS = '\u253C'           # ┼


    def __init__(self):
        self.reset_board()


    def _draw_square(self, board: list):
        draw_board = self._draw_board(board)

        rows = len(draw_board) + 3
        columns = len(draw_board[0]) + 3

        for height in range(rows):
            for width in range(columns):
                if height == 0:
                    if width % 2 == 0:
                        print(' ', end='')
                    else:
                        if width == 3 or width == 7 or width == 11:
                            print(width // 4, end='')   # Imprime la coordenada
                        else:
                            print(' ', end='')
                elif height == 1:
                    # Dibuja la barra superior del cuadrado
                    if width == 0:
                        print(' ', end='')
                    elif width == 1:
                        print(self._TOP_LEFT, end='')
                    elif width >= (columns - 1):
                        print(self._TOP_RIGHT, end='')
                    else:
                        print(self._HORIZONTAL, end='')
                elif height == rows - 1:
                    # Dibuja la barra inferior del cuadrado
                    if width == 0:
                        print(' ', end='')
                    elif width == 1:
                        print(self._BOTTOM_LEFT, end='')
                    elif width >= (columns - 1):
                        print(self._BOTTOM_RIGHT, end='')
                    else:
                        print(self._HORIZONTAL, end='')
                else:
                    if width == 0:
                        if height % 2 == 0:
                            print(height // 2 - 1, end='')     # Imprime la coordenada
                        else:
                            print(' ', end='')
                    elif width == 1:
                        print(self._VERTICAL, end='')
                    elif width >= (columns - 1):
                        print(self._VERTICAL, end='')
                    else:
                        # Dibuja el tablero en el interior del cuadro
                        print(draw_board[height - 2][width - 2], end='')
            print()


    def _draw_board(self, board: list):
        rows = 5
        columns = 11

        result = []

        for row in range(rows):
            board_list = []
            position = 0

            for col in range(columns):
                if row % 2 == 0:
                    if col % 2 == 0:
                        board_list.append(' ')
                    else:
                        if col == 3 or col == 7:
                            board_list.append(self._VERTICAL)
                        else:
                            board_list.append(board[row // 2][position])
                            position += 1
                else:
                    if col == 3 or col == 7:
                        board_list.append(self._CROSS)
                    else:
                        board_list.append(self._HORIZONTAL)
            result.append(board_list)
        return result


    def reset_board(self):
        for row in range(3):
            for col in range(3):
                self._board[row][col] = ' '


    def display_board(self):
        self._draw_square(self._board)


    def make_move(self, row: int, col: int, symbol):
        if self._board[row][col] == ' ':
            self._board[row][col] = symbol
            return True
        else:
            return False


    def is_board_full(self):
        for row in range(3):
            for col in range(3):
                if self._board[row][col] == ' ':
                    return False
        return True


    def check_win(self, symbol):
        # Verificar diagonal principal
        is_win = True
        for row in range(3):
            if self._board[row][row] != symbol:
                is_win = False
                break
        if is_win:
            return True  # Hay un ganador en la diagonal principal

        # Verificar diagonal inversa
        is_win = True
        for row in range(3):
            if self._board[row][2 - row] != symbol:
                is_win = False
                break
        if is_win:
            return True  # Hay un ganador en la diagonal inversa

        # Verificar filas
        for row in range(3):
            is_win = True
            for col in range(3):
                if self._board[row][col] != symbol:
                    is_win = False
                    break
            if is_win:
                return True  # Hay un ganador en la fila actual

        # Verificar columnas
        for col in range(3):
            is_win = True
            for row in range(3):
                if self._board[row][col] != symbol:
                    is_win = False
                    break
            if is_win:
                return True  # Hay un ganador en la columna actual

        return False  # No hay ganador


class Player:
    def __init__(self, player_name, player_symbol):
        self.player_name = player_name
        self.player_symbol = player_symbol


    def get_name(self):
        return self.player_name


    def get_symbol(self):
        return self.player_symbol


class GameManager:
    def __init__(self, player_1_name, player_1_symbol, player_2_name, player_2_symbol):
        self.player_1 = Player(player_1_name, player_1_symbol)
        self.player_2 = Player(player_2_name, player_2_symbol)

        self.game_board = GameBoard()

        self.current_turn = 1


    def play_game(self):
        game_over = False

        while not game_over:
            self.game_board.display_board()

            current_player = self.player_1 if self.current_turn % 2 == 1 else self.player_2

            print(f"Turno de: {current_player.get_name()}")
            row = int(input("Ingresa la Fila: "))
            col = int(input("Ingresa la Columna: "))

            if self.game_board.make_move(row, col, current_player.get_symbol()):
                if self.game_board.check_win(current_player.get_symbol()):
                    print(f"¡Felicidades, {current_player.get_name()} ! ¡Has ganado!")
                    game_over = True
                    break
                elif self.game_board.is_board_full():
                    print("El juego ha terminado en empate.")
                    game_over = True
                    break
                else:
                    self.current_turn += 1
            else:
                print("Movimiento inválido. Intenta nuevamente.")

        self.game_board.display_board()


if __name__ == '__main__':
    player_1_symbol = 'X'
    player_2_symbol = 'O'

    player_1_name = input("Jugador 1, ingresa tu nombre: ")
    player_2_name = input("Jugador 2, ingresa tu nombre: ")

    game_manager = GameManager(player_1_name, player_1_symbol, player_2_name, player_2_symbol)
    game_manager.play_game()
