package model.card;

import model.BlackJackStatus;
import model.card.vo.Card;
import model.card.vo.TrumpNumber;

import java.util.List;

import static model.BlackJackStatus.*;

public class Cards {
    private static final int VALUE_OF_JQK = 10;
    private final List<Card> values;
    private int sumOfCardValues;

    public static Cards generate(List<Card> initialCards) {
        return new Cards(initialCards);
    }

    private Cards(List<Card> initialCards) {
        values = initialCards;
    }

    public void add(Card newCard) {
        values.add(newCard);
    }

    public BlackJackStatus getStatus() {
        sumOfCardValues = 0;
        addNumberValue();
        if (sumOfCardValues < 21) {
            return LOWER_THAN_21;
        }
        if (sumOfCardValues > 21) {
            return HIGHER_THAN_21;
        }
        return BLACKJACK;
    }

    public boolean isHigherThan16() {
        sumOfCardValues = 0;
        addNumberValue();
        return sumOfCardValues > 16;
    }

    private void addNumberValue() {
        values.stream()
                .filter(card -> !card.getNumber().isA())
                .forEach(card -> addNotA(card.getNumber()));
        if (values.stream().anyMatch(Card::isA)) {
            addA();
        }
    }

    private void addNotA(TrumpNumber number) {
        if (number.isJQK()) {
            sumOfCardValues += VALUE_OF_JQK;
        }
        if (!number.isJQK()) {
            sumOfCardValues += Integer.parseInt(number.value());
        }
    }

    private void addA() {
        if (isSumHigherThan21IfAIsCalculatedAs11()) {
            sumOfCardValues += 1;
        }
        if (!isSumHigherThan21IfAIsCalculatedAs11()) {
            sumOfCardValues += 11;
        }
    }

    private boolean isSumHigherThan21IfAIsCalculatedAs11() {
        return sumOfCardValues >= 11;
    }
}