package model.participant;

import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
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
        Participant participant = new Participant(initialCards);
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
        Participant participant = new Participant(initialCards);
        boolean actual = participant.hasBlackJackCard();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndHasBlackJackCards() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.generate(10, 1), Card.generate(11, 1)),
                        false),
                Arguments.of(Arrays.asList(Card.generate(1, 1), Card.generate(10, 1)),
                        true)
        );
    }
}