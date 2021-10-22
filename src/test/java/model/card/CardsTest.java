package model.card;

import model.BlackJackStatus;
import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {
    @ParameterizedTest
    @DisplayName("카드의 합이 21을 초과하는지 반환한다.")
    @MethodSource("provideBlackJackStatus")
    void getSumOfNumbers(List<Card> providedCards, BlackJackStatus expected) {
        Cards cards = Cards.generate(providedCards);
        BlackJackStatus actual = cards.getStatus();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBlackJackStatus() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(Card.generate(1, 1), Card.generate(11, 1)),
                        BlackJackStatus.BLACKJACK),
                Arguments.of(
                        Arrays.asList(Card.generate(1, 1), Card.generate(12, 1),
                                Card.generate(4, 3)),
                        BlackJackStatus.LOWER_THAN_21),
                Arguments.of(
                        Arrays.asList(Card.generate(1, 1), Card.generate(8, 1)),
                        BlackJackStatus.LOWER_THAN_21),
                Arguments.of(
                        Arrays.asList(Card.generate(11, 1), Card.generate(12, 1),
                                Card.generate(4, 3)),
                        BlackJackStatus.BUST)
        );
    }

    @ParameterizedTest
    @DisplayName("카드의 합이 16보다 큰 지 반환한다.")
    @MethodSource("provideCardsAndHigherThan16")
    void isHigherThan16(List<Card> initialCards, boolean expected) {
        Cards cards = Cards.generate(initialCards);
        boolean actual = cards.isHigherThan16();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndHigherThan16() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.generate(8, 1), Card.generate(9, 1)),
                        true),
                Arguments.of(Arrays.asList(Card.generate(7, 1), Card.generate(9, 1)),
                        false)
        );
    }

    @Test
    @DisplayName("새 카드를 뽑는다.")
    void draw() {
        List<Card> initialCards =
                new ArrayList<>(Arrays.asList(Card.generate(1, 1), Card.generate(2, 1)));
        Cards cards =  Cards.generate(initialCards);
        Card eight = Card.generate(8, 1);
        cards.add(eight);
        BlackJackStatus actual = cards.getStatus();
        BlackJackStatus expected = BlackJackStatus.BLACKJACK;
        assertThat(actual).isEqualTo(expected);
    }
}