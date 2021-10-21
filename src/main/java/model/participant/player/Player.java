package model.participant.player;

import model.BlackJackStatus;
import model.card.Cards;
import model.card.vo.Card;

import java.util.List;

public class Player {
    private final Cards cards;
    private final Name name;

    public static Player participate(final String name, final List<Card> initialCards) {
        return new Player(name, initialCards);
    }

    private Player(final String name, final List<Card> initialCards) {
        this.name = Name.create(name);
        this.cards = Cards.generate(initialCards);
    }

    public Name getName() {
        return name;
    }

    public void drawNewCard(Card newCard) {
        cards.draw(newCard);
    }

    public boolean isDefeater() {
        return cards.getStatus() == BlackJackStatus.HIGHER_THAN_21;
    }
}