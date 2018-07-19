package tictactoe;

import static tictactoe.Player.O;
import static tictactoe.Player.X;
import static tictactoe.Status.GAME_ON;

public class Game {

    private final Player nextPlayer;

    public Game() {
        nextPlayer = X;
    }

    private Game(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public GameState state() {
        return new GameState(GAME_ON, nextPlayer);
    }

    public Game play() {
        return new Game(O);
    }
}
