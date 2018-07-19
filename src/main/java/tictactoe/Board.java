package tictactoe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static tictactoe.Square.*;

public class Board {

    private final Map<Square, Player> takenSquares;

    public Board() {
        takenSquares = new HashMap<>();
    }

    private Board(Map<Square, Player> takenSquares) {
        this.takenSquares = takenSquares;
    }

    public boolean alreadyTaken(Square toPlay) {
        return takenSquares.keySet().contains(toPlay);
    }

    public Board take(Square toPlay, Player player) {
        var newBoard = new HashMap<Square, Player>(takenSquares);
        newBoard.put(toPlay, player);
        return new Board(newBoard);
    }

    public boolean isFull() {
        return takenSquares.size() == 9;
    }

    public boolean hasWon(Player currentPlayer) {
        var winningCombos = Stream.of(
                Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
                Stream.of(CENTRE_LEFT, CENTRE_MIDDLE, CENTRE_RIGHT),
                Stream.of(BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTRE_LEFT, BOTTOM_LEFT),
                Stream.of(TOP_MIDDLE, CENTRE_MIDDLE, BOTTOM_MIDDLE),
                Stream.of(TOP_RIGHT, CENTRE_RIGHT, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTRE_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_RIGHT, CENTRE_MIDDLE, BOTTOM_LEFT)
        );
        return winningCombos.anyMatch(combo -> combo.allMatch(squaresTakenBy(currentPlayer)::contains));
    }

    private Set<Square> squaresTakenBy(Player player) {
        return takenSquares.entrySet().stream()
                .filter(entry -> entry.getValue() == player)
                .map(entry -> entry.getKey())
                .collect(toSet());
    }
}
