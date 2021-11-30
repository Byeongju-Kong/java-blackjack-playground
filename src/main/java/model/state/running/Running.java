package model.state.running;

import model.card.Cards;
import model.state.Created;

public abstract class Running extends Created {
    protected Running(Cards initialCards) {
        super(initialCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int profit(final int money, final Cards dealerCards) {
        throw new IllegalArgumentException("아직 Hit 상태라 수익을 알 수 없습니다.");
    }
}