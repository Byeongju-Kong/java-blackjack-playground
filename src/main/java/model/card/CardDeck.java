package model.card;

import model.card.vo.Card;
import model.card.vo.TrumpNumber;
import model.card.vo.TrumpShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardDeck {
    private static final int KIND_OF_NUMBER = 12;
    private static final int KIND_OF_SHAPE = 4;

    private static final List<TrumpNumber> trumpNumbers = Arrays.asList(TrumpNumber.values());
    private static final List<TrumpShape> trumpShapes = Arrays.asList(TrumpShape.values());
    private final List<Card> providedCards;
    private final Random random;

    public CardDeck() {
        providedCards = new ArrayList<>();
        random = new Random();
    }

    public Cards provideInitialCards() {
        return Cards.from(new ArrayList<>(Arrays.asList(provideNewCard(), provideNewCard())));
    }

    public Card provideNewCard() {
        Card newCard;
        do {
            TrumpNumber trumpNumber = trumpNumbers.get(generateRandomIndex(KIND_OF_NUMBER));
            TrumpShape trumpShape = trumpShapes.get(generateRandomIndex(KIND_OF_SHAPE));
            newCard = Card.of(trumpNumber, trumpShape);
        } while (isProvidedAlready(newCard));
        providedCards.add(newCard);
        return newCard;
    }

    int generateRandomIndex(final int boundary) {
        return random.nextInt(boundary);
    }

    boolean isProvidedAlready(final Card newCard) {
        return providedCards.contains(newCard);
    }
}