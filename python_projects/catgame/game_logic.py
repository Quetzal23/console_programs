class GameLogic:
    def __init__(self):
        self.boar = [
            [' ', ' ', ' '],
            [' ', ' ', ' '],
            [' ', ' ', ' ']
        ]

        self.player_1_symbol = ' '
        self.player_2_symbol = ' '


    def get_board(self):
        return self.boar


    def choose_player_symbol(self):
        print("Jugador 1.\nElige tu ficha:")
        print("- X")
        print("- O")

        symbol = input("").upper()
        if symbol == 'X' or symbol == 'O':
            self.player_1_symbol = symbol
            self.player_2_symbol = 'X' if self.player_1_symbol == 'O' else 'O'
        else:
            print("Error, ficha no valida.\nSe seleccionó al Jugador 1 la ficha por defecto: 'O' ")
            self.player_1_symbol = 'O'
            self.player_2_symbol = 'X'
        print()
