package model.state.finished;

import model.card.Cards;

public class Win extends Stay {
    public Win(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1.0;
    }
}