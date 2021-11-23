package model.card;

import model.card.vo.Card;

import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> values;
    private Sum sumOfCardValues;

    public static Cards from(List<Card> initialCards) {
        return new Cards(initialCards);
    }

    private Cards(List<Card> initialCards) {
        values = initialCards;
        sumOfCardValues = Sum.from(initialCards);
    }

    public List<Card> getCards() {
        return values;
    }

    public void add(Card newCard) {
        values.add(newCard);
        sumOfCardValues = Sum.from(values);
    }

    public int getSumOfCardValues() {
        return sumOfCardValues.value();
    }

    public boolean isLowerThan16() {
        return sumOfCardValues.value() <= 16;
    }

    public boolean isHigherThan21() {
        return sumOfCardValues.value() > 21;
    }

    public boolean hasHigherSumOfCardValuesThan(final Cards anotherCards) {
        if (anotherCards.isHigherThan21()) {
            return true;
        }
        return this.sumOfCardValues.value() >= anotherCards.sumOfCardValues.value();
    }

    public boolean hasSameSum(final Cards anotherCards) {
        return this.sumOfCardValues.value() == anotherCards.sumOfCardValues.value();
    }

    public boolean hasSumOf21ComposedWithTwoCard() {
        return values.size() == 2 && sumOfCardValues.value() == 21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards = (Cards) o;
        return Objects.equals(values, cards.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}