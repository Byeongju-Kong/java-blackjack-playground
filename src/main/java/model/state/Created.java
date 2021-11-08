package model.state;

import model.card.Cards;

import java.util.Objects;

public abstract class Created implements State {
    protected final Cards cards;

    protected Created(Cards initialCards) {
        this.cards = initialCards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Created created = (Created) o;
        return Objects.equals(cards, created.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}