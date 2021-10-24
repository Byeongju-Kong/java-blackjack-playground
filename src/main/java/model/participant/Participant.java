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

    public boolean hasHigherCards(final Participant another) {
        return cards.hasHigherSumOfCardValuesThan(another.cards);
    }

    public boolean hasCardsHigherThan16() {
        return cards.isHigherThan16();
    }

    public boolean doesOccurBust() {
        return cards.getStatus() == BlackJackStatus.BUST;
    }

    public boolean hasBlackJackCard() {
        return cards.getStatus() == BlackJackStatus.BLACKJACK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }
}