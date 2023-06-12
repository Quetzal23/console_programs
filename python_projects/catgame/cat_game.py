from draw import Draw
from game_logic import GameLogic


class CatGame:
    def __init__(self):
        game_logic = GameLogic()
        draw = Draw()

        game_logic.choose_player_symbol()

        board = game_logic.get_board()
        draw.draw_square(board)


if __name__ == '__main__':
    game = CatGame()
