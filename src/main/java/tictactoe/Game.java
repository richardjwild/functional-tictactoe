package tictactoe;

import static tictactoe.Player.O;
import static tictactoe.Player.X;
import static tictactoe.Status.GAME_ON;
import static tictactoe.Status.SQUARE_ALREADY_PLAYED;

public class Game {

    private final Status status;
    private final Player currentPlayer;
    private final Board board;

    public Game() {
        currentPlayer = null;
        board = new Board();
        status = GAME_ON;
    }

    private Game(Status status, Board board, Player currentPlayer) {
        this.status = status;
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public GameState state() {
        return new GameState(status, nextPlayer());
    }

    private Player nextPlayer() {
        if (currentPlayer == null)
            return X;
        else
            return currentPlayer == X ? O : X;
    }

    public Game play(Square toPlay) {
        if (board.alreadyTaken(toPlay))
            return new Game(SQUARE_ALREADY_PLAYED, board, currentPlayer);
        else
            return new Game(GAME_ON, board.take(toPlay), nextPlayer());
    }
}
