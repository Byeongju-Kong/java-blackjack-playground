package model;

import model.vo.Card;

import java.util.*;

public class CardDeck {
    private static final int KIND_OF_NUMBER = 12;
    private static final int KIND_OF_SHAPE = 4;
    private final List<Card> providedCars = new ArrayList<>();
    private final Random random = new Random();

    protected CardDeck() {
    }

    public static CardDeck shuffle() {
        return new CardDeck();
    }

    public Card provide() {
        Card newCard;
        do {
            newCard = Card.generate(generateRandomIndex(KIND_OF_NUMBER), generateRandomIndex(KIND_OF_SHAPE));
        } while (isProvidedAlready(newCard));
        providedCars.add(newCard);
        return newCard;
    }

    protected int generateRandomIndex(final int boundary) {
        return random.nextInt(boundary);
    }

    protected boolean isProvidedAlready(final Card newCard) {
        return providedCars.contains(newCard);
    }
}