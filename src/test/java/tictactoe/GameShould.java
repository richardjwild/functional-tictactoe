package tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;
import static tictactoe.Player.O;
import static tictactoe.Player.X;
import static tictactoe.Square.TOP_LEFT;
import static tictactoe.Square.TOP_MIDDLE;
import static tictactoe.Status.GAME_ON;
import static tictactoe.Status.SQUARE_ALREADY_PLAYED;

@DisplayName("Game adjudicator should")
public class GameShould {

    @Test
    void wait_for_x_to_play_first() {
        var game = new Game();

        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
    }

    @Test
    void alternate_the_players() {
        var game = new Game();
        game = game.play(TOP_LEFT);
        game = game.play(TOP_MIDDLE);

        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
    }

    @Test
    void not_allow_a_square_to_be_played_twice() {
        var game = new Game();
        game = game.play(TOP_LEFT);
        game = game.play(TOP_LEFT);

        assertThat(game.state()).isEqualTo(new GameState(SQUARE_ALREADY_PLAYED, O));
    }
}
