package model.participant;

import model.BlackJackStatus;
import model.card.Cards;
import model.card.vo.Card;

import java.util.List;

public class Participant {
    Cards cards;

    public Participant(final List<Card> initialCards) {
        this.cards = Cards.generate(initialCards);
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