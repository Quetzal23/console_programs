class CatGame:
    def __init__(self):
        self.setup_constants()


    def setup_constants(self):
        # Caracteres ASCII
        self.top_left = '\u250C'  # ┌
        self.top_right = '\u2510'  # ┐
        self.bottom_left = '\u2514'  # └
        self.bottom_right = '\u2518'  # ┘
        self.horizontal = '\u2500'  # ─
        self.vertical = '\u2502'  # │
        self.cross = '\u253C'  # ┼


    def draw_board(self, board):
        rows = 5
        columns = 11

        result = []

        for row in range(rows):
            row_elements = []
            position = 0

            for col in range(columns):
                if row % 2 == 0:
                    if col % 2 == 0:
                        row_elements.append(' ')
                    else:
                        if col == 3 or col == 7:
                            row_elements.append(self.vertical)
                        else:
                            row_elements.append(board[row // 2][position])
                            position += 1
                else:
                    if col == 3 or col == 7:
                        row_elements.append(self.cross)
                    else:
                        row_elements.append(self.horizontal)
            result.append(row_elements)
        return result


    def draw_square(self):
        board = [
            ['o', 'o', 'o'],
            ['o', 'o', 'o'],
            ['o', 'o', 'o']
        ]

        draw_board = self.draw_board(board)

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
                        print(self.top_left, end='')
                    elif width >= (columns - 1):
                        print(self.top_right, end='')
                    else:
                        print(self.horizontal, end='')
                elif height == rows - 1:
                    # Dibuja la barra inferior del cuadrado
                    if width == 0:
                        print(' ', end='')
                    elif width == 1:
                        print(self.bottom_left, end='')
                    elif width >= (columns - 1):
                        print(self.bottom_right, end='')
                    else:
                        print(self.horizontal, end='')
                else:
                    if width == 0:
                        if height % 2 == 0:
                            print(height // 2 - 1, end='')     # Imprime la coordenada
                        else:
                            print(' ', end='')
                    elif width == 1:
                        print(self.vertical, end='')
                    elif width >= (columns - 1):
                        print(self.vertical, end='')
                    else:
                        # Dibuja el tablero en el interior del cuadro
                        print(draw_board[height - 2][width - 2], end='')
            print()


if __name__ == '__main__':
    game = CatGame()
    game.draw_square()
