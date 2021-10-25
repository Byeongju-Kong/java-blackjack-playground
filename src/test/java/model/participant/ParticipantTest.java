package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import model.participant.vo.Name;
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

class ParticipantTest {
    @ParameterizedTest
    @DisplayName("참가자의 숫자가 21이 넘어 패배하였는지에 대한 여부를 알려준다.")
    @MethodSource("provideNewCardAndIsDefeater")
    void isDefeater(Card newCard, boolean expected) {
        List<Card> initialCards =
                new ArrayList<>(Arrays.asList(Card.generate(4, 1), Card.generate(9, 1)));
        Participant participant = Participant.participate("Brandon", initialCards);
        participant.draw(newCard);
        boolean actual = participant.doesOccurBust();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNewCardAndIsDefeater() {
        return Stream.of(
                Arguments.of(Card.generate(7, 1), false),
                Arguments.of(Card.generate(9, 2), true)
        );
    }

    @ParameterizedTest
    @DisplayName("참가자의 카드가 블랙잭인지 반환한다.")
    @MethodSource("provideCardsAndHasBlackJackCards")
    void hasBlackJackCards(List<Card> initialCards, boolean expected) {
        Participant participant = Participant.participate("Brandon", initialCards);
        boolean actual = participant.hasBlackJackCard();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드들을 반환한다.")
    void getCards() {
        List<Card> initialCards = Arrays.asList(
                Card.generate(6, 1), Card.generate(11, 1),
                Card.generate(1, 1));
        Participant participant = Participant.participate("Brandon", initialCards);
        Cards cards = participant.getCards();
        assertThat(cards.getCards()).isEqualTo(initialCards);
    }

    private static Stream<Arguments> provideCardsAndHasBlackJackCards() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.generate(10, 1), Card.generate(11, 1)),
                        false),
                Arguments.of(Arrays.asList(Card.generate(1, 1), Card.generate(10, 1)),
                        true)
        );
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void hasName() {
        List<Card> anyCards = Arrays.asList(Card.generate(10, 1), Card.generate(11, 1));
        Participant participant = Participant.participate("Brandon", anyCards);
        Name actual = participant.getName();
        Name expected = Name.create("Brandon");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Participant 객체를 받아 자신의 카드 합이 더 큰지 반환한다.")
    void hasHigherCardsThan() {
        List<Card> firstCards = Arrays.asList(Card.generate(1, 1), Card.generate(10, 1));
        List<Card> secondCards = Arrays.asList(Card.generate(3, 1), Card.generate(10, 1));
        Participant first = Participant.participate("Brandon", firstCards);
        Participant second = Participant.participate("Henry", secondCards);
        assertThat(first.hasHigherCardsThan(second)).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 카드가 16 이하 인지 반환한다.")
    void hasCardsLowerThan16() {
        List<Card> lowerCardsThan16 = Arrays.asList(Card.generate(3, 1), Card.generate(10, 1));
        Participant participant = Participant.participate("Brandon", lowerCardsThan16);
        assertThat(participant.hasCardsLowerThan16()).isTrue();
    }

    @Test
    @DisplayName("이름을 기준으로 같은 참가자인지 반환한다.")
    void equals() {
        List<Card> firstCards = Arrays.asList(Card.generate(1, 1), Card.generate(10, 1));
        List<Card> secondCards = Arrays.asList(Card.generate(3, 1), Card.generate(10, 1));
        Participant first = Participant.participate("Brandon", firstCards);
        Participant second = Participant.participate("Brandon", secondCards);
        assertThat(first.equals(second)).isTrue();
    }
}