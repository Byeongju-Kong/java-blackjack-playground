package model.state;

import model.card.Cards;
import model.card.vo.Card;

public interface State {
    State draw(final Card newCard);

    State stay();

    boolean isFinished();

    Cards cards();

    double profit(final int bettingMoney, final Cards dealerCards);
}
