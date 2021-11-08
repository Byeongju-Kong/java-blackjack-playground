package model.participant;

import model.card.Cards;

public class Dealer extends Participant {
    public static Dealer participate(final Cards initialCards) {
        return new Dealer(initialCards);
    }

    private Dealer(final Cards initialCards) {
        super("Dealer", initialCards);
    }

    public boolean hasCardsLowerThan16() {
        return state.cards().isLowerThan16();
    }
}
