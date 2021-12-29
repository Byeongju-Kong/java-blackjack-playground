package model.card;

import model.card.vo.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.card.vo.TrumpNumber.A;
import static model.card.vo.TrumpNumber.TWO;
import static model.card.vo.TrumpShape.DIAMOND;
import static model.card.vo.TrumpShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {
    private CardDeck cardProvider;
    private final List<Card> providedCards = new ArrayList<>();
    private final int[] indices = new int[]{0, 0, 1, 1};
    private int orderOfIndices = 0;

    @BeforeEach
    void setUp() {
        cardProvider = new CardDeck() {
            @Override
            protected int generateRandomIndex(final int boundary) {
                return indices[orderOfIndices++];
            }

            @Override
            protected boolean isProvidedAlready(final Card newCard) {
                return providedCards.contains(newCard);
            }
        };
    }

    @Test
    @DisplayName("초기 카드 2장을 반환한다.")
    void provideInitialCards() {
        Cards actualInitialCards = cardProvider.provideInitialCards();
        Card expectedFirstCard = Card.of(A, SPADE);
        Card expectedSecondCard = Card.of(TWO, DIAMOND);
        Cards expectedInitialCards = Cards.from(Arrays.asList(expectedFirstCard, expectedSecondCard));
        assertThat(actualInitialCards).isEqualTo(expectedInitialCards);
    }

    @Test
    @DisplayName("이미 제공되지 않은 랜덤 카드를 반환한다.")
    void provide() {
        providedCards.add(Card.of(A, SPADE));
        Card card = cardProvider.provideNewCard();
        assertThat(card).isEqualTo(Card.of(TWO, DIAMOND));
    }
}