package model.card;

import model.BlackJackStatus;
import model.card.vo.Card;

import java.util.List;

import static model.BlackJackStatus.*;

public class Cards {
    private final List<Card> values;
    private Sum sumOfCardValues;

    public static Cards generate(List<Card> initialCards) {
        return new Cards(initialCards);
    }

    private Cards(List<Card> initialCards) {
        values = initialCards;
        sumOfCardValues = Sum.create(initialCards);
    }

    public List<Card> getCards() {
        return values;
    }

    public void add(Card newCard) {
        values.add(newCard);
        sumOfCardValues = Sum.create(values);
    }

    public BlackJackStatus getStatus() {
        if (sumOfCardValues.value() == 21) {
            return BLACKJACK;
        }
        if (sumOfCardValues.value() > 21) {
            return BUST;
        }
        return LOWER_THAN_21;
    }

    public boolean isHigherThan16() {
        return sumOfCardValues.value() > 16;
    }

    public boolean hasHigherSumOfCardValuesThan(final Cards cards) {
        return this.sumOfCardValues.value() >= cards.sumOfCardValues.value();
    }
}