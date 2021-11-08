package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import model.participant.vo.Name;
import model.state.BlackJack;
import model.state.Finished;
import model.state.Hit;
import model.state.State;

import java.util.Objects;

public class Participant {
    protected Name name;
    State state;

    public static Participant participate(final String name, final Cards initialCards) {
        return new Participant(name, initialCards);
    }

    Participant(final String name, final Cards initialCards) {
        if (initialCards.hasSumOf21ComposedWithTwoCard()) {
            this.state = new BlackJack(initialCards);
        } else if (!initialCards.hasSumOf21ComposedWithTwoCard()) {
            this.state = new Hit(initialCards);
        }
        this.name = Name.create(name);
    }

    public void draw(final Card newCards) {
        state = state.draw(newCards);
    }

    public Cards getCards() {
        return state.cards();
    }

    public Name getName() {
        return name;
    }

    public boolean hasName(final String name) {
        return this.name.value().equals(name);
    }

    public boolean hasHigherCardsThan(final Participant another) {
        return state.cards().hasHigherSumOfCardValuesThan(another.state.cards());
    }

    public boolean hasBlackJackCard() {
        return state instanceof BlackJack;
    }

    public boolean canDrawCards() {
        return !(state instanceof Finished);
    }

    public void stay() {
        state = state.stay();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }
}