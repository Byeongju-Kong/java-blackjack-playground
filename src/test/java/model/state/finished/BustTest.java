package model.state.finished;

import model.card.Cards;
import model.card.vo.Card;
import model.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static model.card.vo.TrumpNumber.*;
import static model.card.vo.TrumpShape.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BustTest {
    private final Cards initialCards = Cards.from(
            Arrays.asList(Card.of(SEVEN, SPADE), Card.of(EIGHT, DIAMOND),
                    Card.of(NINE, CLOVER)));
    private final State state = new Bust(initialCards);

    @Test
    @DisplayName("draw 메서드를 호출하면, 더 이상 카드를 뽑을 수 없기 때문에 자기 자신을 반환한다.")
    void draw() {
        Card newCard = Card.of(A, SPADE);
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

    @ParameterizedTest
    @DisplayName("딜러카드가 Bust라면 0을 반환하고 아니라면 배팅금액의 -1배를 반환한다..")
    @MethodSource("provideDealerCardsAndExpectedProfit")
    void profit(final Cards dealerCards, final int expectedProfit) {
        int bettingMoney = 10000;
        int actualProfit = state.profit(bettingMoney, dealerCards);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> provideDealerCardsAndExpectedProfit() {
        return Stream.of(
                Arguments.of(Cards.from(Arrays.asList(Card.of(J, DIAMOND), Card.of(SIX, HEART),
                        Card.of(SEVEN, DIAMOND))), 0),
                Arguments.of(Cards.from(Arrays.asList(Card.of(J, DIAMOND), Card.of(SEVEN, HEART))), -10000)
        );
    }
}