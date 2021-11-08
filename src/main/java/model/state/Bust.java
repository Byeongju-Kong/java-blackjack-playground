package model.state;

import model.card.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    double earningRate() {
        return -1.0;
    }
}