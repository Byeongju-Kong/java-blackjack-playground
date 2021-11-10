package model.game;

import model.card.Cards;
import model.card.vo.Card;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameTest {
    List<Card> cards = Arrays.asList(
            Card.generate(1, 1), Card.generate(10, 2),
            Card.generate(1, 2), Card.generate(2, 3),
            Card.generate(2, 3), Card.generate(5, 3),
            Card.generate(8, 3), Card.generate(3, 2));
    private int cardIndex = 0;
    List<String> names = Arrays.asList("Brandon", "Henry");

    Game game = new Game(names) {
        @Override
        public List<Participant> participate(final List<String> names) {
            List<Participant> participants = new ArrayList<>();
            names.forEach(name -> participants.add(Participant.participate(name, provideInitialCards())));
            participants.add(Participant.participate("Dealer", provideInitialCards()));
            return participants;
        }

        private Cards provideInitialCards() {
            return Cards.generate(new ArrayList<>(Arrays.asList(cards.get(cardIndex++), cards.get(cardIndex++))));
        }

        @Override
        protected Card provideNewCard() {
            return cards.get(cardIndex++);
        }
    };

    @ParameterizedTest
    @DisplayName("이름 값을 받아 이름에 해당 하는 참가자가 추가 카드를 받을 수 있는지 반환한다.")
    @CsvSource({"Brandon, false", "Henry, true"})
    void canGiveNewCardTo(String name, boolean expected) {
        boolean actual = game.canGiveNewCardTo(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("이름 값을 받아 이름에 해당하는 참가자에게 새로운 카드를 지급한다.")
    void giveNewCardTo() {
        game.giveNewCardTo("Henry");
        Cards actual = game.getCardsOf("Henry");
        Cards expected = Cards.generate(Arrays.asList(
                Card.generate(1, 2), Card.generate(2, 3),
                Card.generate(8, 3)));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("이름 값을 받아 이름에 해당하는 참가자의 카드를 반환한다.")
    void getCardsOf() {
        List<Card> actual = game.getCardsOf("Brandon").getCards();
        List<Card> expected = Arrays.asList(Card.generate(1, 1), Card.generate(10, 2));
        assertThat(actual).isEqualTo(expected);
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
    void checkDealerHasCardsLowerThan16(Cards dealerCards, boolean expected) {
        cardIndex = 0;
        Game game = new Game(names) {
            @Override
            public List<Participant> participate(final List<String> names) {
                List<Participant> participants = new ArrayList<>();
                names.forEach(name -> participants.add(Participant.participate(name, provideInitialCards())));
                participants.add(Dealer.participate(dealerCards));
                return participants;
            }

            private Cards provideInitialCards() {
                return Cards.generate(new ArrayList<>(Arrays.asList(cards.get(cardIndex++), cards.get(cardIndex++))));
            }
        };
        assertThat(game.checkDealerHasCardsLowerThan16()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDealerCardsAndLowerThan16() {
        return Stream.of(
                Arguments.of(Cards.generate(new ArrayList<>(
                        Arrays.asList(Card.generate(1, 1), Card.generate(8, 1)))), false),
                Arguments.of(Cards.generate(new ArrayList<>(
                        Arrays.asList(Card.generate(2, 1), Card.generate(8, 1)))), true)
        );
    }
}