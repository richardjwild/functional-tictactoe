package tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;
import static tictactoe.Player.X;
import static tictactoe.Status.GAME_ON;

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
        game = game.play();
        game = game.play();

        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
    }
}
