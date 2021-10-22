package model.participant;

import model.BlackJackStatus;
import model.card.Cards;
import model.card.vo.Card;
import model.participant.player.vo.Name;

import java.util.List;
import java.util.Objects;

public class Participant {
    protected Name name;
    Cards cards;

    public Participant(final Name name, final List<Card> initialCards) {
        this.cards = Cards.generate(initialCards);
        this.name = name;
    }

    public void draw(final Card newCards) {
        cards.add(newCards);
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

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}