package model.card;

import model.card.vo.Card;
import model.card.vo.TrumpNumber;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Sum {
    private final int value;

    public static Sum from(final List<Card> cards) {
        return new Sum(cards);
    }

    private Sum(final List<Card> cards) {
        value = addNumberValue(cards);
    }

    private int addNumberValue(final List<Card> cards) {
        int countOfA = (int) cards.stream().filter(Card::isA).count();
        int sum = addNotA(cards);
        for (int index = 0; index < countOfA; index++) {
            sum = addA(sum);
        }
        return sum;
    }

    private int addNotA(final List<Card> cards) {
        AtomicInteger sumOfNotA = new AtomicInteger();
        cards.stream()
                .filter(card -> !card.isA())
                .map(Card::getNumber)
                .mapToInt(TrumpNumber::value)
                .forEach(sumOfNotA::addAndGet);
        return sumOfNotA.get();
    }

    private int addA(final int sum) {
        if (isSumHigherThan21IfAIsCalculatedAs11(sum)) {
            return sum + 1;
        }
        return sum + 11;
    }

    private boolean isSumHigherThan21IfAIsCalculatedAs11(final int sum) {
        return sum >= 11;
    }

    public int value() {
        return value;
    }
}