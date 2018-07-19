package tictactoe;

import static tictactoe.Player.X;
import static tictactoe.Status.GAME_ON;

public class Game {
    public GameState state() {
        return new GameState(GAME_ON, X);
    }
}
