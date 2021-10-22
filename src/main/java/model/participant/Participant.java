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

    public boolean isDefeater() {
        return cards.getStatus() == BlackJackStatus.HIGHER_THAN_21;
    }

    public boolean hasBlackJackCard() {
        return cards.getStatus() == BlackJackStatus.BLACKJACK;
    }
}