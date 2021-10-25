package model.game;

import model.card.vo.Card;
import model.participant.Participant;
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
import static org.junit.jupiter.api.Assertions.assertAll;

class GameTest {
    List<Card> initialCards = Arrays.asList(
            Card.generate(1, 1), Card.generate(4, 2),
            Card.generate(1, 2), Card.generate(2, 3),
            Card.generate(2, 3), Card.generate(5, 3),
            Card.generate(3, 3));
    private int cardIndex = 0;
    String[] names = new String[]{"Brandon", "Henry"};

    Game game = new Game(names) {
        @Override
        public List<Participant> participate(final String[] names) {
            List<Participant> participants = new ArrayList<>();
            Arrays.stream(names)
                    .forEach(name -> participants.add(Participant.participate(name, provideInitialCards())));
            participants.add(Participant.participate("Dealer", provideInitialCards()));
            return participants;
        }

        private List<Card> provideInitialCards() {
            return new ArrayList<>(Arrays.asList(initialCards.get(cardIndex++), initialCards.get(cardIndex++)));
        }
    };

    @Test
    @DisplayName("참가자들을 반환한다.")
    void getParticipants() {
        List<Participant> participants = game.getParticipants();
        List<Card> expectedCardsOfBrandon =
                Arrays.asList(Card.generate(1, 1), Card.generate(4, 2));
        List<Card> expectedCardsOfHenry =
                Arrays.asList(Card.generate(1, 2), Card.generate(2, 3));
        List<Card> expectedCardsOfDealer =
                Arrays.asList(Card.generate(2, 3), Card.generate(5, 3));
        assertAll(
                () -> assertThat(participants.get(0).getName()).isEqualTo(Name.create("Brandon")),
                () -> assertThat(participants.get(0).getCards().getCards()).isEqualTo(expectedCardsOfBrandon),
                () -> assertThat(participants.get(1).getName()).isEqualTo(Name.create("Henry")),
                () -> assertThat(participants.get(1).getCards().getCards()).isEqualTo(expectedCardsOfHenry),
                () -> assertThat(participants.get(2).getName()).isEqualTo(Name.create("Dealer")),
                () -> assertThat(participants.get(2).getCards().getCards()).isEqualTo(expectedCardsOfDealer)
        );
    }

    @Test
    @DisplayName("승자(들)를 반환한다")
    void getWinner() {
        List<Name> winners = game.getWinner();
        assertAll(
                () -> assertThat(winners.get(0)).isEqualTo(Name.create("Brandon")),
                () -> assertThat(winners.size()).isEqualTo(1)
        );
    }

    @ParameterizedTest
    @DisplayName("Dealer의 카드가 16이하면 false를 반환하고 ")
    @MethodSource("provideDealerCardsAndLowerThan16")
    void checkDealerHasCardsLowerThan16(List<Card> dealerCards, boolean expected) {
        cardIndex = 0;
        Game game = new Game(names) {
            @Override
            public List<Participant> participate(final String[] names) {
                List<Participant> participants = new ArrayList<>();
                Arrays.stream(names)
                        .forEach(name -> participants.add(Participant.participate(name, provideInitialCards())));
                participants.add(Participant.participate("Dealer", dealerCards));
                return participants;
            }

            private List<Card> provideInitialCards() {
                return new ArrayList<>(Arrays.asList(initialCards.get(cardIndex++), initialCards.get(cardIndex++)));
            }
        };
        assertThat(game.checkDealerHasCardsLowerThan16()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDealerCardsAndLowerThan16() {
        return Stream.of(
                Arguments.of(new ArrayList<>(
                        Arrays.asList(Card.generate(1, 1), Card.generate(8, 1))), false),
                Arguments.of(new ArrayList<>(
                        Arrays.asList(Card.generate(2, 1), Card.generate(8, 1))), true)
        );
    }
}