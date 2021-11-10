package model.state.running;

import model.card.Cards;
import model.card.vo.Card;
import model.state.State;
import model.state.finished.Bust;
import model.state.finished.Stay;

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