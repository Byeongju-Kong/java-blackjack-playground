package model.state.finished;

import model.card.Cards;
import model.card.vo.Card;
import model.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BlackJackTest {
    private final Cards initialCards = Cards.generate(
            Arrays.asList(Card.generate(1, 1), Card.generate(10, 2)));
    private final State state = new BlackJack(initialCards);

    @Test
    @DisplayName("draw 메서드를 호출하면 예외를 발생시킨다.")
    void draw() {
        Card newCard = Card.generate(1, 1);
        assertThatIllegalArgumentException().isThrownBy(() -> state.draw(newCard))
                .withMessage("이미 Bust or Stay or BlackJack 상태입니다.");
    }

    @Test
    @DisplayName("stay 메서드를 호출하면 예외를 발생시킨다.")
    void stay() {
        assertThatIllegalArgumentException().isThrownBy(state::stay)
                .withMessage("이미 Bust or Stay or BlackJack 상태입니다.");
    }

    @Test
    @DisplayName("게임을 더 진행할 수 있는지 반환한다.")
    void isFinished() {
        boolean actual = state.isFinished();
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("베팅 금액을 받아 수익을 반환한다.")
    void profit() {
        int bettingMoney = 10000;
        int actual = state.profit(bettingMoney);
        int expected = 15000;
        assertThat(actual).isEqualTo(expected);
    }
}