package tictactoe;

import java.util.HashSet;
import java.util.Set;

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
}
