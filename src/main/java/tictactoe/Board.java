package tictactoe;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static tictactoe.Square.*;

public class Board {

    private final Set<Square> takenSquares;

    public Board() {
        takenSquares = new HashSet<>();
    }

    private Board(Set<Square> takenSquares) {
        this.takenSquares = takenSquares;
    }

    public boolean alreadyTaken(Square toPlay) {
        return takenSquares.contains(toPlay);
    }

    public Board take(Square toPlay) {
        var newBoard = new HashSet<Square>(takenSquares);
        newBoard.add(toPlay);
        return new Board(newBoard);
    }

    public boolean isFull() {
        return takenSquares.size() == 9;
    }

    public boolean hasWon() {
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
        return winningCombos.anyMatch(combo -> combo.allMatch(takenSquares::contains));
    }
}
