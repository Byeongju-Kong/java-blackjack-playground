package model.participant;

import model.card.vo.Card;

import java.util.List;

public class Dealer extends Participant {
    public static Dealer participate(List<Card> initialCards) {
        return new Dealer(initialCards);
    }

    private Dealer(List<Card> initialCards) {
        super(initialCards);
    }

    public boolean hasCardsHigherThan16() {
        return cards.isHigherThan16();
    }
}