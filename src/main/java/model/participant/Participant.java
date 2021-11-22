package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import model.state.State;

public abstract class Participant {
    State state;

    abstract boolean canDrawCards();

    public Cards getCards() {
        return state.cards();
    }

    public void draw(final Card newCard) {
        state.draw(newCard);
    }
}