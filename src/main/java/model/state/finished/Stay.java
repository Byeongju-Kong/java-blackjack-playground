package model.state.finished;

import model.card.Cards;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public int profit(final int bettingMoney, final Cards dealerCards) {
        if (dealerCards.hasSumOf21ComposedWithTwoCard()) {
            return bettingMoney * -1;
        } else if (cards.hasSameSum(dealerCards)) {
            return 0;
        } else if (cards.hasHigherSumOfCardValuesThan(dealerCards)) {
            return bettingMoney;
        }
        return bettingMoney * -1;
    }
}