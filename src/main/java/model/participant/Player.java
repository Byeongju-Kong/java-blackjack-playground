package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import model.participant.vo.Name;
import model.state.State;
import model.state.finished.BlackJack;
import model.state.finished.Stay;
import model.state.running.Hit;

import java.util.List;
import java.util.Objects;

public class Player {
    protected Name name;
    State state;

    public static Player participate(final String name, final Cards initialCards) {
        return new Player(name, initialCards);
    }

    Player(final String name, final Cards initialCards) {
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

    public boolean hasName(final String name) {
        return this.name.value().equals(name);
    }

    public boolean hasHigherCardsThan(final List<Player> others) {
        return others.stream().allMatch(this::hasHigherCardsThan);
    }

    public boolean hasHigherCardsThan(final Player another) {
        return state.cards().hasHigherSumOfCardValuesThan(another.state.cards());
    }

    public boolean canDrawCards() {
        return !state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }

    public boolean hasStayState() {
        return state instanceof Stay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return Objects.equals(name, that.name);
    }
}