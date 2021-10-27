package model.participant;

import model.BlackJackStatus;
import model.card.Cards;
import model.card.vo.Card;
import model.participant.vo.Name;

import java.util.List;
import java.util.Objects;

public class Participant {
    protected Name name;
    Cards cards;

    public static Participant participate(final String name, final List<Card> initialCards) {
        return new Participant(name, initialCards);
    }

    private Participant(final String name, final List<Card> initialCards) {
        this.cards = Cards.generate(initialCards);
        this.name = Name.create(name);
    }

    public void draw(final Card newCards) {
        cards.add(newCards);
    }

    public Cards getCards() {
        return cards;
    }

    public Name getName() {
        return name;
    }

    public boolean hasName(final String name) {
        return this.name.value().equals(name);
    }

    public boolean hasHigherCardsThan(final Participant another) {
        return cards.hasHigherSumOfCardValuesThan(another.cards);
    }

    public boolean hasCardsLowerThan16() {
        return cards.isLowerThan16();
    }

    public boolean hasBlackJackCard() {
        return cards.getStatus() == BlackJackStatus.BLACKJACK;
    }

    public boolean canDrawCards() {
        return cards.getStatus() == BlackJackStatus.LOWER_THAN_21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }
}