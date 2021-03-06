package model.state.finished;

import model.card.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public int profit(final int bettingMoney, final Cards dealerCards) {
        if(dealerCards.isHigherThan21()) {
            return 0;
        }
        return bettingMoney * -1;
    }
}