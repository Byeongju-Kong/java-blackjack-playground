package model.state.finished;

import model.card.Cards;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    public double earningRate() {
        return 0.0;
    }
}