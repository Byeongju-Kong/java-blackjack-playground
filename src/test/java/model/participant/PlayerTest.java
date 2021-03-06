package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static model.card.vo.TrumpNumber.*;
import static model.card.vo.TrumpShape.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    private final Cards initialCards = Cards.from(
            new ArrayList<>(Arrays.asList(Card.of(EIGHT, SPADE), Card.of(J, HEART))));
    private final Player player = Player.of("Chris", 10000, initialCards);

    @ParameterizedTest
    @DisplayName("이름 값을 받아 자신이 갖고 있는 이름과 같은지 반환한다.")
    @CsvSource({"Chris, true", "Brandon, false"})
    void hasName(final String name, final boolean expected) {
        boolean actual = player.hasName(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드를 반환한다.")
    void getCards() {
        Cards actual = player.getCards();
        assertThat(actual).isEqualTo(initialCards);
    }

    @Test
    @DisplayName("카드를 한장 추가한다.")
    void draw() {
        Card newCard = Card.of(THREE, DIAMOND);
        player.draw(newCard);
        Cards actual = player.getCards();
        Cards expected = Cards.from(
                Arrays.asList(Card.of(EIGHT, SPADE), Card.of(J, HEART),
                        Card.of(THREE, DIAMOND)));
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("카드를 더 뽑을 수 있는지 반환한다.")
    @MethodSource("provideInitialCardsAndCanDrawCards")
    void canDrawCards(final Cards initialCards, final boolean expected) {
        Player player = Player.of("Chris", 10000, initialCards);
        boolean actual = player.canDrawCards();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideInitialCardsAndCanDrawCards() {
        return Stream.of(
                Arguments.of(Cards.from(Arrays.asList(
                        Card.of(A, DIAMOND), Card.of(NINE, DIAMOND))), true),
                Arguments.of(Cards.from(Arrays.asList(
                        Card.of(A, DIAMOND), Card.of(J, DIAMOND))), false)
        );
    }

    @Test
    @DisplayName("상태를 Stay로 변경한다.")
    void stay() {
        player.stay();
        boolean ableToDraw = player.canDrawCards();
        assertThat(ableToDraw).isFalse();
    }

    @ParameterizedTest
    @DisplayName("수익을 반환한다.")
    @MethodSource("provideDealerCardsAndExpectedProfit")
    void getProfit(final Cards dealerCards, int expectedProfit) {
        player.stay();
        double actualProfit = player.getProfitAgainst(dealerCards);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> provideDealerCardsAndExpectedProfit() {
        return Stream.of(
                Arguments.of(Cards.from(Arrays.asList(
                        Card.of(EIGHT, SPADE), Card.of(NINE, CLOVER))), 10000),
                Arguments.of(Cards.from(Arrays.asList(
                        Card.of(J, SPADE), Card.of(NINE, CLOVER))), -10000)
        );
    }

    private final Cards anotherCards = Cards.from(
            Arrays.asList(Card.of(A, HEART), Card.of(SEVEN, HEART)));

    @ParameterizedTest
    @DisplayName("stay 상태에서, 딜러의 카드를 받아 자신의 카드와 비교 후, 수익을 반환한다.")
    @MethodSource("provideCardsAndExpectedProfit")
    void getProfitAgainst(final Cards cards, final int expectedProfit) {
        Player player = Player.of("Chris", 10000, cards);
        Cards dealerCards = Cards.from(Arrays.asList(Card.of(EIGHT, DIAMOND), Card.of(NINE, HEART)));
        player.stay();
        double actualProfit = player.getProfitAgainst(dealerCards);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> provideCardsAndExpectedProfit() {
        return Stream.of(
                Arguments.of(Cards.from(Arrays.asList(Card.of(SIX, CLOVER), Card.of(J, DIAMOND))),
                        -10000),
                Arguments.of(Cards.from(Arrays.asList(Card.of(EIGHT, CLOVER), Card.of(J, DIAMOND))),
                        10000)
        );
    }

    @ParameterizedTest
    @DisplayName("이름을 기준으로 같은 객체인지 반환한다.")
    @MethodSource("provideNamesAndExpectationOfEqualsAndHashCode")
    void equals(final String name, final boolean expected) {
        Player another = Player.of(name, 20000, anotherCards);
        boolean actual = player.equals(another);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("이름을 기준으로 같은 해쉬코드를 반환한다.")
    @MethodSource("provideNamesAndExpectationOfEqualsAndHashCode")
    void hashCode(final String name, final boolean expected) {
        Player anotherPlayer = Player.of(name, 20000, anotherCards);
        int playerHashCode = player.hashCode();
        int anotherPlayerHashCode = anotherPlayer.hashCode();
        boolean actual = playerHashCode == anotherPlayerHashCode;
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNamesAndExpectationOfEqualsAndHashCode() {
        return Stream.of(
                Arguments.of("Chris", true),
                Arguments.of("Brandon", false)
        );
    }
}