package view;

import model.card.Cards;

import java.util.List;
import java.util.Map;

public interface OutputView {
    void showGameStart(List<String> playerNames);

    void showCardsOf(final String name, final Cards cards);

    void alertNewCardOfDealer(final boolean drawingNewCard);

    void showFinalResultOf(final String name, final Cards cards);

    void showFinalMoney(final Map<String, Integer> finalMoneys);
}
