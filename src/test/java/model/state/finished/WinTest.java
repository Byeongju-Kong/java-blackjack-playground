package model.state.finished;

import model.card.Cards;
import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinTest {
    @Test
    @DisplayName("수익률을 반환한다.")
    void earningRate() {
        Cards cards = Cards.from(
                Arrays.asList(Card.of(10, 1), Card.of(7, 2)));
        Finished win = new Win(cards);
        double actual = win.earningRate();
        double expected = 1.0;
        assertThat(actual).isEqualTo(expected);
    }
}