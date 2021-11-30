package model.state.running;

import model.card.Cards;
import model.card.vo.Card;
import model.state.State;
import model.state.finished.Bust;
import model.state.finished.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class HitTest {
    private final Cards initialCards = Cards.from(new ArrayList<>(
            Arrays.asList(Card.of(7, 1), Card.of(8, 2))));
    private final State state = new Hit(initialCards);

    @Test
    @DisplayName("draw로 새 카드를 추가하여 Bust가 발생하지 않으면 새로운 Hit 객체를 반환한다.")
    void draw_newHit() {
        Card newCard = Card.of(5, 1);
        State actualStateAfterDraw = state.draw(newCard);
        Cards expectedCards = Cards.from(Arrays.asList(
                        Card.of(7, 1), Card.of(8, 2), Card.of(5, 1)));
        State expectedStateAfterDraw = new Hit(expectedCards);
        assertThat(actualStateAfterDraw).isEqualTo(expectedStateAfterDraw);
    }

    @Test
    @DisplayName("draw로 새 카드를 추가하여 Bust가 발생하면 새로운 Bust 객체를 반환한다.")
    void draw_Bust() {
        Card newCard = Card.of(9, 1);
        State actualStateAfterDraw = state.draw(newCard);
        Cards expectedCards = Cards.from(Arrays.asList(
                Card.of(7, 1), Card.of(8, 2), Card.of(9, 1)));
        State expectedStateAfterDraw = new Bust(expectedCards);
        assertThat(actualStateAfterDraw).isEqualTo(expectedStateAfterDraw);
    }

    @Test
    @DisplayName("stay 메소드를 호출하면 현재 카드를 가진 Stay 객체를 반환한다.")
    void stay() {
        State actual = state.stay();
        State expected = new Stay(initialCards);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드를 반환한다.")
    void cards() {
        Cards cards = state.cards();
        assertThat(cards).isEqualTo(initialCards);
    }

    @Test
    @DisplayName("더 이상 게임을 진행할 수 없는 상태인지를 반환한다.")
    void isFinished() {
        boolean actual = state.isFinished();
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Hit 상태에서 profit 메소드를 호출하면 예외를 발생시킨다.")
    void profit() {
        Cards dealerCards = Cards.from(Arrays.asList(Card.of(1, 1), Card.of(2, 1)));
        assertThatIllegalArgumentException().isThrownBy(() -> state.profit(10000, dealerCards))
                .withMessage("아직 Hit 상태라 수익을 알 수 없습니다.");
    }
}