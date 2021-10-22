package model.participant;

import model.BlackJackStatus;
import model.card.Cards;
import model.card.vo.Card;
import model.participant.player.vo.Name;

import java.util.List;

public class Participant {
    protected Name name;
    Cards cards;

    public Participant(final String name, final List<Card> initialCards) {
        this.cards = Cards.generate(initialCards);
        this.name = Name.create(name);
    }

    public boolean hasName(final Name name) {
        return this.name.equals(name);
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
}