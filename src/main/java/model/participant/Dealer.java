package model.participant;

import model.BlackJackStatus;
import model.card.Cards;
import model.card.vo.Card;

import java.util.List;

public class Dealer {
    private final Cards cards;

    public static Dealer participate(List<Card> initialCards) {
        return new Dealer(initialCards);
    }

    private Dealer(List<Card> initialCards) {
        this.cards = Cards.generate(initialCards);
    }

    public boolean hasCardsHigherThan16() {
        return cards.isHigherThan16();
    }

    public void draw(Card newCards) {
        cards.draw(newCards);
    }

    public boolean isDefeater() {
        return cards.getStatus() == BlackJackStatus.HIGHER_THAN_21;
    }

    public boolean hasBlackJackCard() {
        return cards.getStatus() == BlackJackStatus.BLACKJACK;
    }
}