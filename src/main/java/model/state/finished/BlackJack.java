package model.state.finished;

import model.card.Cards;
import model.state.State;

public class BlackJack extends Finished {
    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1.5;
    }

    @Override
    public State win() {
        throw new IllegalArgumentException("블랙잭 상태이라, 필승입니다.");
    }

    @Override
    public State lose() {
        throw new IllegalArgumentException("블랙잭 상태이라, 필승입니다.");
    }
}