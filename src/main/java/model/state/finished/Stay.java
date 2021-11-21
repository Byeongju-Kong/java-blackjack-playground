package model.state.finished;

import model.card.Cards;
import model.state.State;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 0.0;
    }

    public State win() {
        return new Win(cards);
    }

    public State lose() {
        return new Lose(cards);
    }
}