package model.state.finished;

import model.card.Cards;
import model.card.vo.Card;
import model.state.Created;
import model.state.State;

public abstract class Finished extends Created {
    protected Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card newCard) {
        return this;
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int profit(int bettingMoney) {
        return (int) (bettingMoney * earningRate());
    }

    abstract double earningRate();
}