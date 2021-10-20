package model;

import model.vo.Card;
import model.vo.TrumpNumber;

import java.util.List;

import static model.BlackJackStatus.*;

public class Cards {
    private final List<Card> values;
    private int sumOfCardValues;

    public static Cards generate(List<Card> initialCards) {
        return new Cards(initialCards);
    }

    private Cards(List<Card> initialCards) {
        values = initialCards;
    }

    public void draw(Card newCard) {
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

    private void addNumberValue() {
        values.stream()
                .filter(card -> !card.getNumber().isA())
                .forEach(card -> addNotA(card.getNumber()));
        Card a = Card.generate(1, 1);
        if (values.contains(a)) {
            addA();
        }
    }

    private void addNotA(TrumpNumber number) {
        if (number.isJQK()) {
            sumOfCardValues += 10;
        }
        if (!number.isJQK()) {
            sumOfCardValues += Integer.parseInt(number.value());
        }
    }

    private void addA() {
        if (sumOfCardValues >= 11) {
            sumOfCardValues += 1;
        }
        if (sumOfCardValues < 11) {
            sumOfCardValues += 11;
        }
    }
}