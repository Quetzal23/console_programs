class Cat:
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

        rows = len(draw_board) + 2
        columns = len(draw_board[0]) + 2

        for height in range(rows):
            for width in range(columns):
                if height == 0:
                    if width == 0:
                        print(self.top_left, end='')
                    elif width >= (columns - 1):
                        print(self.top_right, end='')
                    else:
                        print(self.horizontal, end='')
                elif height == rows - 1:
                    if width == 0:
                        print(self.bottom_left, end='')
                    elif width >= (columns - 1):
                        print(self.bottom_right, end='')
                    else:
                        print(self.horizontal, end='')
                else:
                    if width == 0:
                        print(self.vertical, end='')
                    elif width >= (columns - 1):
                        print(self.vertical, end='')
                    else:
                        print(draw_board[height - 1][width - 1], end='')
            print()


game = Cat()
game.draw_square()
