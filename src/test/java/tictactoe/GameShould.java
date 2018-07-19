package tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static tictactoe.Player.NOBODY;
import static tictactoe.Player.O;
import static tictactoe.Player.X;
import static tictactoe.Square.*;
import static tictactoe.Square.BOTTOM_RIGHT;
import static tictactoe.Square.TOP_LEFT;
import static tictactoe.Square.TOP_MIDDLE;
import static tictactoe.Status.*;

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
        var game = play(TOP_LEFT, TOP_MIDDLE, TOP_LEFT);

        assertThat(game.state()).isEqualTo(new GameState(SQUARE_ALREADY_PLAYED, X));
    }

    private Game play(Square... squares) {
        return Arrays.stream(squares)
                .reduce(new Game(), Game::play, (a, b) -> null);
    }

    // X' O' X'
    // O' X' X'
    // O' X O
    @Test
    void recognise_a_draw() {
        var game = play(
                TOP_LEFT,
                TOP_MIDDLE,
                TOP_RIGHT,
                CENTRE_LEFT,
                CENTRE_MIDDLE,
                BOTTOM_LEFT,
                CENTRE_RIGHT,
                BOTTOM_RIGHT,
                BOTTOM_MIDDLE);

        assertThat(game.state()).isEqualTo(new GameState(DRAW, NOBODY));
    }

    @ParameterizedTest
    @CsvSource({
            "TOP_LEFT,CENTRE_LEFT,TOP_MIDDLE,CENTRE_MIDDLE,TOP_RIGHT",
            "CENTRE_LEFT,TOP_LEFT,CENTRE_MIDDLE,TOP_MIDDLE,CENTRE_RIGHT",
            "BOTTOM_LEFT,TOP_LEFT,BOTTOM_MIDDLE,TOP_MIDDLE,BOTTOM_RIGHT",
            "TOP_LEFT,TOP_MIDDLE,CENTRE_LEFT,CENTRE_MIDDLE,BOTTOM_LEFT",
            "TOP_MIDDLE,TOP_LEFT,CENTRE_MIDDLE,CENTRE_LEFT,BOTTOM_MIDDLE",
            "TOP_RIGHT,TOP_LEFT,CENTRE_RIGHT,CENTRE_LEFT,BOTTOM_RIGHT",
            "TOP_LEFT,BOTTOM_LEFT,CENTRE_MIDDLE,TOP_RIGHT,BOTTOM_RIGHT",
            "TOP_RIGHT,BOTTOM_RIGHT,CENTRE_MIDDLE,TOP_LEFT,BOTTOM_LEFT"
    })
    void recognise_when_x_has_won(Square s1, Square s2, Square s3, Square s4, Square s5) {
        var game = play(s1, s2, s3, s4, s5);

        assertThat(game.state()).isEqualTo(new GameState(X_HAS_WON, NOBODY));
    }

    @Test
    void recognise_when_o_has_won() {
        var game = play(
                BOTTOM_LEFT,
                TOP_LEFT,
                CENTRE_LEFT,
                TOP_MIDDLE,
                CENTRE_MIDDLE,
                TOP_RIGHT);

        assertThat(game.state()).isEqualTo(new GameState(O_HAS_WON, NOBODY));
    }

    @Test
    void not_permit_play_after_game_is_won() {
        var game = play(
                TOP_LEFT,
                CENTRE_LEFT,
                TOP_MIDDLE,
                CENTRE_MIDDLE,
                TOP_RIGHT,
                CENTRE_RIGHT);

        assertThat(game.state()).isEqualTo(new GameState(X_HAS_WON, NOBODY));
    }

    // X O X
    // O X X
    // O O X
    @Test
    void recognise_win_when_won_on_final_square() {
        var game = play(TOP_LEFT,
                TOP_MIDDLE,
                TOP_RIGHT,
                CENTRE_LEFT,
                CENTRE_MIDDLE,
                BOTTOM_LEFT,
                CENTRE_RIGHT,
                BOTTOM_MIDDLE,
                BOTTOM_RIGHT);

        assertThat(game.state()).isEqualTo(new GameState(X_HAS_WON, NOBODY));
    }
}
