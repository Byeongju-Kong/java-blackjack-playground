package model.state.finished;

import model.card.Cards;
import model.card.vo.Card;
import model.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {
    private final Cards initialCards = Cards.generate(
            Arrays.asList(Card.generate(1, 1), Card.generate(10, 2)));
    private final State state = new BlackJack(initialCards);

    @Test
    @DisplayName("draw 메서드를 호출하면, 더 이상 카드를 뽑을 수 없기 때문에 자기 자신을 반환한다.")
    void draw() {
        Card newCard = Card.generate(1, 1);
        State actualStateAfterDraw = state.draw(newCard);
        assertThat(actualStateAfterDraw).isEqualTo(state);
    }

    @Test
    @DisplayName("stay 메서드를 호출하면, 더 이상 상태를 변경할 수 없기 때문에 자기 자신을 반환한다.")
    void stay() {
        State actual = state.stay();
        assertThat(actual).isEqualTo(state);
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