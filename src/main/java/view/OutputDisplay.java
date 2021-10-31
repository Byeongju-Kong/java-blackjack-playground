package view;

import model.card.Cards;

import java.util.List;
import java.util.Map;

public class OutputDisplay implements OutputView {
    private static final String DELIMITER = ",";

    @Override
    public void showGameStart(List<String> playerNames) {
        StringBuilder message = new StringBuilder("Dealer와");
        playerNames.forEach(name -> message.append(name).append(DELIMITER));
        message.deleteCharAt(message.lastIndexOf(DELIMITER));
        message.append("에게 두 장의 카드를 나누었습니다.");
        System.out.println(message);
    }

    @Override
    public void showCardsOf(final String name, final Cards cards) {
        StringBuilder message = new StringBuilder(name + "의 카드");
        cards.getCards().forEach(card -> message.append(card).append(DELIMITER));
        message.deleteCharAt(message.lastIndexOf(DELIMITER));
        System.out.println(message);
    }

    @Override
    public void alertNewCardOfDealer(final boolean drawingNewCard) {
        if (drawingNewCard) {
            System.out.println("Dealer의 카드는 16이하라 한장의 카드를 더 받았습니다.");
        }
        if (!drawingNewCard) {
            System.out.println("Dealer의 카드는 17이상이라 한장의 카드를 더 받지 않았습니다.");
        }
    }

    @Override
    public void showFinalResultOf(final String name, final Cards cards) {
        StringBuilder message = new StringBuilder(name + "카드 : ");
        cards.getCards().forEach(card -> message.append(card).append(DELIMITER));
        message.deleteCharAt(message.lastIndexOf(DELIMITER));
        message.append(" - 결과 : ").append(cards.getSumOfCardValues());
        System.out.println(message);
    }

    @Override
    public void showFinalMoney(final Map<String, Integer> finalMoneys) {
        System.out.println("## 최종 수익");
        finalMoneys.keySet().
                forEach(name -> System.out.println(name + " : " + finalMoneys.get(name)));
    }
}