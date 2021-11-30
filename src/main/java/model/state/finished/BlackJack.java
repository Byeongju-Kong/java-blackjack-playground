package model.state.finished;

import model.card.Cards;

public class BlackJack extends Finished {
    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public int profit(final int bettingMoney, final Cards dealerCards) {
        if (dealerCards.hasSumOf21ComposedWithTwoCard()) {
            return 0;
        }
        return (int) (bettingMoney * 1.5);
    }
}