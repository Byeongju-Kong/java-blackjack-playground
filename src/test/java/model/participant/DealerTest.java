package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static model.card.vo.TrumpNumber.*;
import static model.card.vo.TrumpShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @ParameterizedTest
    @DisplayName("딜러가 추가적인 한 장의 카드를 받을 수 있는지 반환한다.")
    @MethodSource("provideCardsAndLowerThan16")
    void hasCardsLowerThan16(Cards initialCards, boolean expected) {
        Dealer dealer = Dealer.from(initialCards);
        boolean actual = dealer.canDrawCards();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndLowerThan16() {
        return Stream.of(
                Arguments.of(Cards.from(
                        Arrays.asList(Card.of(EIGHT, SPADE), Card.of(SEVEN, SPADE))),
                        true),
                Arguments.of(Cards.from(
                        Arrays.asList(Card.of(EIGHT, SPADE), Card.of(NINE, SPADE))),
                        false)
        );
    }
}