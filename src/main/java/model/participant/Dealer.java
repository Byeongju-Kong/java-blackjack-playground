package model.participant;

import model.card.Cards;
import model.state.running.Hit;

public class Dealer extends Participant {
    public static Dealer from(final Cards initialCards) {
        return new Dealer(initialCards);
    }

    private Dealer(final Cards initialCards) {
        state = new Hit(initialCards);
    }

    @Override
    public boolean canDrawCards() {
        return state.cards().isLowerThan16();
    }
}
