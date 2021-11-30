package view;

import model.card.Cards;
import model.card.vo.Card;

import java.util.List;
import java.util.Map;

public interface OutputView {
    void showGameStart(List<String> playerNames);

    void showOneOfInitialCardsOf(final String name, final Card card);

    void showCardsOf(final String name, final Cards cards);

    void alertNewCardOfDealer(final boolean drawingNewCard);

    void showFinalResultOf(final String name, final Cards cards);

    void showFinalMoney(final Map<String, Integer> finalMoneys);
}
