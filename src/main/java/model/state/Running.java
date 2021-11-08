package model.state;

import model.card.Cards;

public abstract class Running extends Created {
    protected Running(Cards initialCards) {
        super(initialCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int profit(final int bettingMoney) {
        return 0;
    }
}