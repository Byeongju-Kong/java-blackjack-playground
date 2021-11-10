package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import model.participant.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    private final Cards initialCards = Cards.generate(
            new ArrayList<>(Arrays.asList(Card.generate(4, 1), Card.generate(9, 1))));
    private final Player participant = Player.participate("Brandon", initialCards);

    @ParameterizedTest
    @DisplayName("카드를 더 draw 할 수 있는지 반환한다.")
    @MethodSource("provideNewCardAndIsDefeater")
    void isDefeater(Card newCard, boolean expected) {
        participant.draw(newCard);
        boolean actual = participant.canDrawCards();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNewCardAndIsDefeater() {
        return Stream.of(
                Arguments.of(Card.generate(7, 1), true),
                Arguments.of(Card.generate(9, 2), false)
        );
    }

    @Test
    @DisplayName("카드들을 반환한다.")
    void getCards() {
        Cards cards = participant.getCards();
        assertThat(cards.getCards()).isEqualTo(initialCards.getCards());
    }

    @ParameterizedTest
    @DisplayName("문자열을 받아 자신의 이름 값과 같은지 반환한다.")
    @CsvSource({"Brandon, true", "Henry, false"})
    void hasName(String name, boolean expected) {
        boolean actual = participant.hasName(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Participant 객체를 받아 자신의 카드 합이 더 큰지 반환한다.")
    void hasHigherCardsThan() {
        Cards anotherCards = Cards.generate(
                Arrays.asList(Card.generate(4, 2), Card.generate(8, 2)));
        Player another = Player.participate("Henry", anotherCards);
        assertThat(participant.hasHigherCardsThan(another)).isTrue();
    }

    @Test
    @DisplayName("상태를 stay로 변경한다.")
    void stay() {
        participant.stay();
        boolean actual = participant.canDrawCards();
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("이름을 기준으로 같은 참가자인지 반환한다.")
    void equals() {
        Cards firstCards = Cards.generate(
                Arrays.asList(Card.generate(1, 1), Card.generate(10, 1)));
        Cards secondCards = Cards.generate(
                Arrays.asList(Card.generate(3, 1), Card.generate(10, 1)));
        Player first = Player.participate("Brandon", firstCards);
        Player second = Player.participate("Brandon", secondCards);
        assertThat(first.equals(second)).isTrue();
    }
}