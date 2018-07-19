package tictactoe;

import static tictactoe.Player.O;
import static tictactoe.Player.X;
import static tictactoe.Status.GAME_ON;

public class Game {

    private final Player currentPlayer;

    public Game() {
        currentPlayer = null;
    }

    private Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameState state() {
        return new GameState(GAME_ON, nextPlayer());
    }

    private Player nextPlayer() {
        if (currentPlayer == null)
            return X;
        else
            return currentPlayer == X ? O : X;
    }

    public Game play() {
        return new Game(nextPlayer());
    }
}
