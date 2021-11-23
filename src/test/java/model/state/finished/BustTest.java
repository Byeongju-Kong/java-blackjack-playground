package model.state.finished;

import model.card.Cards;
import model.card.vo.Card;
import model.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BustTest {
    private final Cards initialCards = Cards.from(
            Arrays.asList(Card.of(7, 1), Card.of(8, 2),
                    Card.of(9, 4)));
    private final State state = new Bust(initialCards);

    @Test
    @DisplayName("draw 메서드를 호출하면, 더 이상 카드를 뽑을 수 없기 때문에 자기 자신을 반환한다.")
    void draw() {
        Card newCard = Card.of(1, 1);
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
    @DisplayName("게임을 더 진행할 수 는지 반환한다.")
    void isFinished() {
        boolean actual = state.isFinished();
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("베팅 금액을 받아 수익을 반환한다.")
    void profit() {
        int bettingMoney = 10000;
        int actual = state.profit(bettingMoney);
        int expected = -10000;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭 상태에서 win 함수를 호출하면 예외를 발생시킨다.")
    void win_Exception() {
        assertThatIllegalArgumentException().isThrownBy(state::win)
                .withMessage("BUST 상태는, 필패입니다.");
    }

    @Test
    @DisplayName("블랙잭 상태에서 win 함수를 호출하면 예외를 발생시킨다.")
    void lose_Exception() {
        assertThatIllegalArgumentException().isThrownBy(state::lose)
                .withMessage("BUST 상태는, 필패입니다.");
    }
}