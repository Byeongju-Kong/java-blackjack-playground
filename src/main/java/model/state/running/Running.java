package model.state.running;

import model.card.Cards;
import model.state.Created;
import model.state.State;

public abstract class Running extends Created {
    protected Running(Cards initialCards) {
        super(initialCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int profit(final int money) {
        return 0;
    }

    @Override
    public State win() {
        throw new IllegalArgumentException("아직 stay 상태가 아니라 승패를 가를 수 없습니다.");
    }

    @Override
    public State lose() {
        throw new IllegalArgumentException("아직 stay 상태가 아니라 승패를 가를 수 없습니다.");
    }
}