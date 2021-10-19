package model;

import model.vo.Card;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CardProvider {
    private static final int KIND_OF_NUMBER = 12;
    private static final int KIND_OF_SHAPE = 4;
    private final Set<Card> providedCars = new HashSet<>();
    private final Random random = new Random();

    protected CardProvider() {
    }

    public static CardProvider shuffle() {
        return new CardProvider();
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