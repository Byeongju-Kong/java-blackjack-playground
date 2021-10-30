package model.card;

import model.BlackJackStatus;
import model.card.vo.Card;

import java.util.List;

import static model.BlackJackStatus.checkStatus;

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

    public int getSumOfCardValues() {
        return sumOfCardValues.value();
    }

    public BlackJackStatus getStatus() {
        return checkStatus(sumOfCardValues.value());
    }

    public boolean isLowerThan16() {
        return sumOfCardValues.value() <= 16;
    }

    public boolean hasHigherSumOfCardValuesThan(final Cards cards) {
        return this.sumOfCardValues.value() <= 21 &&
                this.sumOfCardValues.value() >= cards.sumOfCardValues.value();
    }
}