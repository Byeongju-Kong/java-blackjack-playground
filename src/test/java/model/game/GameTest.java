package model.game;

import model.card.CardDeck;
import model.card.Cards;
import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private final CardDeck cardDeck = new CardDeck() {
        final Cards cardsOfChris = Cards.from(
                new ArrayList<>(Arrays.asList(Card.of(1, 1), Card.of(10, 2))));
        final Cards cardsOfBrandon = Cards.from(
                new ArrayList<>(Arrays.asList(Card.of(7, 1), Card.of(8, 2))));
        final Cards cardsOfHenry = Cards.from(
                new ArrayList<>(Arrays.asList(Card.of(9, 1), Card.of(6, 2))));
        final Cards cardsOfDealer = Cards.from(
                new ArrayList<>(Arrays.asList(Card.of(11, 1), Card.of(7, 1))));
        final Queue<Cards> initialCards = new LinkedList<>(Arrays.asList(cardsOfChris, cardsOfBrandon, cardsOfHenry, cardsOfDealer));

        @Override
        public Cards provideInitialCards() {
            return initialCards.poll();
        }

        final Queue<Card> additionalCards = new LinkedList<>(
                Arrays.asList(Card.of(8, 4), Card.of(4, 4)));

        @Override
        public Card provideNewCard() {
            return additionalCards.poll();
        }
    };

    private final Map<String, Integer> namesAndMoneys = new LinkedHashMap<>();

    {
        namesAndMoneys.put("Chris", 20000);
        namesAndMoneys.put("Brandon", 30000);
        namesAndMoneys.put("Henry", 10000);
    }

    private final Game game = new Game(namesAndMoneys, cardDeck);

    @ParameterizedTest
    @DisplayName("추가 카드를 지급할 수 있는지 반환한다.")
    @CsvSource({"Chris, false", "Brandon, true", "Henry, true", "Dealer, false"})
    void canGiveNewCardTo(final String name, final boolean expected) {
        boolean actual = game.canGiveNewCardTo(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("새로운 카드를 지급한다.")
    void giveNewCardTo() {
        game.giveNewCardTo("Brandon");
        Cards actualCardsOfBrandon = game.getCardsOf("Brandon");
        Cards expectedCardsOfBrandon = Cards.from(Arrays.asList(
                Card.of(7, 1), Card.of(8, 2), Card.of(8, 4)));
        assertThat(actualCardsOfBrandon).isEqualTo(expectedCardsOfBrandon);
    }

    @Test
    @DisplayName("이름을 가지고 stay 메소드를 호출하면 이름에 해당하는 플레이어의 상태를 Stay로 변경한다.")
    void stay() {
        game.stay("Brandon");
        boolean actual = game.canGiveNewCardTo("Brandon");
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @DisplayName("이름과 수익의 쌍을 반환한다.")
    @CsvSource({"Chris, 30000", "Brandon, -30000", "Henry, 10000", "딜러, -10000"})
    void getProfitOf(final String name, final int expectedProfit) {
        game.giveNewCardTo("Brandon");
        game.giveNewCardTo("Henry");
        game.stay("Henry");
        int actualProfit = game.getProfits().get(name);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }
}