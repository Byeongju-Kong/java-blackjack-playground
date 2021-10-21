package model;

import model.card.CardDeck;
import model.card.vo.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardProviderTest {
    private CardDeck cardProvider;
    private final Set<Card> providedCars = new HashSet<>();
    private final int[] indices = new int[]{1, 1, 2, 2, 3, 3};
    private int orderOfIndices = 0;

    @BeforeEach
    void setUp() {
        providedCars.add(Card.generate(1, 1));
        cardProvider = new CardDeck() {
            @Override
            protected int generateRandomIndex(final int boundary) {
                return indices[orderOfIndices++];
            }

            @Override
            protected boolean isProvidedAlready(final Card newCard) {
                return providedCars.contains(newCard);
            }
        };
    }

    @Test
    @DisplayName("이미 제공되지 않은 랜덤 카드를 반환한다.")
    void provide() {
        Card firstCard = cardProvider.provide();
        Card secondCard = cardProvider.provide();
        assertAll(
                () -> assertThat(firstCard).isEqualTo(Card.generate(2, 2)),
                () -> assertThat(secondCard).isEqualTo(Card.generate(3, 3))
        );
    }
}