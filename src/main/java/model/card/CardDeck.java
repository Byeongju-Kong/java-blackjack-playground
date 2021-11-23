package model.card;

import model.card.vo.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardDeck {
    private static final int KIND_OF_NUMBER = 12;
    private static final int KIND_OF_SHAPE = 4;
    private static final int DEFAULT_ADD_OF_INDEX = 1;
    private final List<Card> providedCards;
    private final Random random;

    CardDeck() {
        providedCards = new ArrayList<>();
        random = new Random();
    }

    public static CardDeck shuffle() {
        return new CardDeck();
    }

    public Cards provideInitialCards() {
        return Cards.from(new ArrayList<>(Arrays.asList(provideNewCard(), provideNewCard())));
    }

    public Card provideNewCard() {
        Card newCard;
        do {
            newCard = Card.of(generateRandomIndex(KIND_OF_NUMBER), generateRandomIndex(KIND_OF_SHAPE));
        } while (isProvidedAlready(newCard));
        providedCards.add(newCard);
        return newCard;
    }

    protected int generateRandomIndex(final int boundary) {
        return random.nextInt(boundary) + DEFAULT_ADD_OF_INDEX;
    }

    protected boolean isProvidedAlready(final Card newCard) {
        return providedCards.contains(newCard);
    }
}