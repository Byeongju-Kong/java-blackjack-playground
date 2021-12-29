package model.card;

import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static model.card.vo.TrumpNumber.*;
import static model.card.vo.TrumpShape.HEART;
import static model.card.vo.TrumpShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

class SumTest {
    @ParameterizedTest
    @DisplayName("카드들을 받아 카드의 합을 반환한다.")
    @MethodSource("provideCardsAndSumOfCards")
    void value(List<Card> cards, int expectedSum) {
        Sum sum = Sum.from(cards);
        int actualSum = sum.value();
        assertThat(actualSum).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> provideCardsAndSumOfCards() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(Card.of(A, SPADE), Card.of(Q, SPADE)),
                        21),
                Arguments.of(
                        Arrays.asList(Card.of(A, SPADE), Card.of(K, SPADE),
                                Card.of(FOUR, HEART)),
                        15),
                Arguments.of(
                        Arrays.asList(Card.of(A, SPADE), Card.of(EIGHT, SPADE)),
                        19),
                Arguments.of(
                        Arrays.asList(Card.of(Q, SPADE), Card.of(K, SPADE),
                                Card.of(FOUR, HEART)),
                        24)
        );
    }
}