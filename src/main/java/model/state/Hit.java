package model.state;

import model.card.Cards;
import model.card.vo.Card;

public class Hit extends Running {
    public Hit(Cards cards) {
        super(cards);
    }

    public State draw(Card newCard) {
        cards.add(newCard);
        if (cards.isHigherThan21()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    public State stay() {
        return new Stay(cards);
    }
}