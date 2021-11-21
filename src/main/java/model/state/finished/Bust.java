package model.state.finished;

import model.card.Cards;
import model.state.State;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    double earningRate() {
        return -1.0;
    }

    @Override
    public State win() {
        throw new IllegalArgumentException("BUST 상태는, 필패입니다.");
    }

    @Override
    public State lose() {
        throw new IllegalArgumentException("BUST 상태는, 필패입니다.");
    }
}