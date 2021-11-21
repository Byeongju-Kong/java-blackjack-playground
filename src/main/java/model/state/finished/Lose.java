package model.state.finished;

import model.card.Cards;

public class Lose extends Stay {
    public Lose(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return -1.0;
    }
}