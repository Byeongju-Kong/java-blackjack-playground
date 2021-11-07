package model.state;

import model.card.Cards;
import model.card.vo.Card;

public interface State {
    State draw(Card newCard);
    State stay();
    boolean isFinished();
    Cards cards();
    int profit(int bettingMoney);
}
