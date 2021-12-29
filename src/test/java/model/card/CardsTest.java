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

import static model.card.vo.TrumpNumber.*;
import static model.card.vo.TrumpShape.*;
import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {
    private final List<Card> initialCards =
            new ArrayList<>(Arrays.asList(Card.of(A, SPADE), Card.of(TWO, SPADE)));

    @Test
    @DisplayName("카드들을 반환한다.")
    void getCars() {
        Cards cards = Cards.from(initialCards);
        List<Card> actual = cards.getCards();
        assertThat(actual).isEqualTo(initialCards);
    }

    @Test
    @DisplayName("카드의 합을 반환한다.")
    void getSumOfCardValues() {
        Cards cards = Cards.from(initialCards);
        int actual = cards.getSumOfCardValues();
        int expected = 13;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("새 카드를 뽑는다.")
    void draw() {
        Cards cards = Cards.from(initialCards);
        Card eight = Card.of(EIGHT, SPADE);
        cards.add(eight);
        Cards expected = Cards.from(
                Arrays.asList(Card.of(A, SPADE), Card.of(TWO, SPADE), Card.of(EIGHT, SPADE)));
        assertThat(cards).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("카드의 합이 16보다 작은지 반환한다.")
    @MethodSource("provideCardsAndLowerThan16")
    void isLowerThan16(List<Card> initialCards, boolean expected) {
        Cards cards = Cards.from(initialCards);
        boolean actual = cards.isLowerThan16();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndLowerThan16() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.of(EIGHT, SPADE), Card.of(NINE, SPADE)),
                        false),
                Arguments.of(Arrays.asList(Card.of(SEVEN, SPADE), Card.of(NINE, SPADE)),
                        true)
        );
    }

    @ParameterizedTest
    @DisplayName("카드의 합이 21보다 큰 지 반환한다.")
    @MethodSource("provideCardsAndHigherThan21")
    void isHigherThan21(List<Card> initialCards, boolean expected) {
        Cards cards = Cards.from(initialCards);
        boolean actual = cards.isHigherThan21();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndHigherThan21() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.of(EIGHT, SPADE), Card.of(NINE, SPADE),
                        Card.of(SEVEN, HEART)), true),
                Arguments.of(Arrays.asList(Card.of(SEVEN, SPADE), Card.of(NINE, SPADE),
                        Card.of(TWO, HEART)), false)
        );
    }

    @ParameterizedTest
    @DisplayName("카드의 개수가 2개이고, 카드의 합이 21인지 반환한다.")
    @MethodSource("provideCardsAndTwoCardOf21")
    void hasSumOf21ComposedWithTwoCard(List<Card> initialCards, boolean expected) {
        Cards cards = Cards.from(initialCards);
        boolean actual = cards.hasSumOf21ComposedWithTwoCard();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndTwoCardOf21() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.of(A, SPADE), Card.of(J, SPADE)),
                        true),
                Arguments.of(Arrays.asList(Card.of(THREE, SPADE), Card.of(J, SPADE)),
                        false),
                Arguments.of(Arrays.asList(Card.of(EIGHT, SPADE), Card.of(NINE, SPADE),
                        Card.of(FOUR, HEART)), false)
        );
    }

    @Test
    @DisplayName("카드를 받아 자신의 카드와 합이 같은지 반환한다.")
    void hasSameSumOfCardValues() {
        Cards cards = Cards.from(initialCards);
        Cards anotherCards = Cards.from(Arrays.asList(Card.of(SIX, HEART), Card.of(SEVEN, DIAMOND)));
        boolean actual = cards.hasSameSum(anotherCards);
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Cards 객체를 받아 자신의 카드합 보다 작은지 반환한다.")
    void hasHigherSumOfCardValuesThan() {
        Cards firstCards =
                Cards.from(Arrays.asList(Card.of(A, SPADE), Card.of(EIGHT, SPADE)));
        Cards secondCards =
                Cards.from(Arrays.asList(Card.of(TWO, SPADE), Card.of(EIGHT, SPADE)));
        boolean actual = firstCards.hasHigherSumOfCardValuesThan(secondCards);
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Cards 객체를 받아 카드들을 기준으로 같은지 반환한다.")
    void equals() {
        Cards firstCards =
                Cards.from(Arrays.asList(Card.of(A, SPADE), Card.of(EIGHT, SPADE)));
        Cards secondCards =
                Cards.from(Arrays.asList(Card.of(A, SPADE), Card.of(EIGHT, SPADE)));
        assertThat(firstCards).isEqualTo(secondCards);
    }
}