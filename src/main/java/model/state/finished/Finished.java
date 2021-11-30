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
        throw new IllegalArgumentException("이미 Bust or Stay or BlackJack 상태입니다.");
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("이미 Bust or Stay or BlackJack 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}