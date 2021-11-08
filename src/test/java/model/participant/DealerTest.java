package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @ParameterizedTest
    @DisplayName("카드의 합이 16 이하인지 반환한다.")
    @MethodSource("provideCardsAndLowerThan16")
    void hasCardsLowerThan16(Cards initialCards, boolean expected) {
        Dealer dealer = Dealer.participate(initialCards);
        boolean actual = dealer.hasCardsLowerThan16();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndLowerThan16() {
        return Stream.of(
                Arguments.of(Cards.generate(
                        Arrays.asList(Card.generate(8, 1), Card.generate(7, 1))),
                        true),
                Arguments.of(Cards.generate(
                        Arrays.asList(Card.generate(8, 1), Card.generate(9, 1))),
                        false)
        );
    }
}