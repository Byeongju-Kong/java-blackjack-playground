package model.card;

import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
                        Arrays.asList(Card.of(1, 1), Card.of(11, 1)),
                        21),
                Arguments.of(
                        Arrays.asList(Card.of(1, 1), Card.of(12, 1),
                                Card.of(4, 3)),
                        15),
                Arguments.of(
                        Arrays.asList(Card.of(1, 1), Card.of(8, 1)),
                        19),
                Arguments.of(
                        Arrays.asList(Card.of(11, 1), Card.of(12, 1),
                                Card.of(4, 3)),
                        24)
        );
    }
}