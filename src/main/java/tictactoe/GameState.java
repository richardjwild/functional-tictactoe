package tictactoe;

public class GameState {
    private final Status status;
    private final Player nextUp;

    public GameState(Status status, Player nextUp) {
        this.status = status;
        this.nextUp = nextUp;
    }

    @Override
    public String toString() {
        return "Status: " + status + ", next up: " + nextUp;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GameState
                && ((GameState) o).status == this.status
                && ((GameState) o).nextUp == this.nextUp;
    }
}
