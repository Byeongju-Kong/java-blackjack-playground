package model.card;

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
import static org.junit.jupiter.api.Assertions.assertAll;

class CardsTest {
    private final List<Card> initialCards =
            new ArrayList<>(Arrays.asList(Card.generate(1, 1), Card.generate(2, 1)));

    @Test
    @DisplayName("카드들을 반환한다.")
    void getCars() {
        Cards cards = Cards.generate(initialCards);
        List<Card> actual = cards.getCards();
        assertThat(actual).isEqualTo(initialCards);
    }

    @Test
    @DisplayName("새 카드를 뽑는다.")
    void draw() {
        Cards cards = Cards.generate(initialCards);
        Card eight = Card.generate(8, 1);
        cards.add(eight);
        Cards expected = Cards.generate(
                Arrays.asList(Card.generate(1, 1), Card.generate(2, 1),
                        Card.generate(8, 1)));
        assertThat(cards).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("카드의 합이 16보다 큰 지 반환한다.")
    @MethodSource("provideCardsAndLowerThan16")
    void isLowerThan16(List<Card> initialCards, boolean expected) {
        Cards cards = Cards.generate(initialCards);
        boolean actual = cards.isLowerThan16();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndLowerThan16() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.generate(8, 1), Card.generate(9, 1)),
                        false),
                Arguments.of(Arrays.asList(Card.generate(7, 1), Card.generate(9, 1)),
                        true)
        );
    }

    @ParameterizedTest
    @DisplayName("카드의 합이 16보다 큰 지 반환한다.")
    @MethodSource("provideCardsAndHigherThan21")
    void isHigherThan21(List<Card> initialCards, boolean expected) {
        Cards cards = Cards.generate(initialCards);
        boolean actual = cards.isHigherThan21();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndHigherThan21() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.generate(8, 1), Card.generate(9, 1),
                        Card.generate(7, 3)), true),
                Arguments.of(Arrays.asList(Card.generate(7, 1), Card.generate(9, 1),
                        Card.generate(2, 3)), false)
        );
    }

    @Test
    @DisplayName("Cards 객체를 받아 자신의 카드합 보다 작은지 반환한다.")
    void hasHigherSumOfCardValuesThan() {
        Cards firstCards =
                Cards.generate(Arrays.asList(Card.generate(1, 1), Card.generate(8, 1)));
        Cards secondCards =
                Cards.generate(Arrays.asList(Card.generate(2, 1), Card.generate(8, 1)));
        boolean actual = firstCards.hasHigherSumOfCardValuesThan(secondCards);
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Cards 객체를 받아 카드들을 기준으로 같은지 반환한다.")
    void equals() {
        Cards firstCards =
                Cards.generate(Arrays.asList(Card.generate(1, 1), Card.generate(8, 1)));
        Cards secondCards =
                Cards.generate(Arrays.asList(Card.generate(1, 1), Card.generate(8, 1)));
        assertThat(firstCards).isEqualTo(secondCards);
    }
}