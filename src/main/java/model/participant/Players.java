package model.participant;

import model.card.Cards;
import model.card.vo.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Players {
    private static final String PLAYER_NOT_FOUNDED_EXCEPTION_MESSAGE = "이름에 맞는 참가자가 없습니다.";
    private final List<Player> values;

    public static Players of(Map<String, Integer> namesAndBettingMoneys, List<Cards> initialCards) {
        return new Players(namesAndBettingMoneys, initialCards);
    }

    private Players(final Map<String, Integer> namesAndBettingMoneys, final List<Cards> initialCards) {
        values = new ArrayList<>();
        namesAndBettingMoneys.keySet()
                .forEach(name -> values.add(Player.of(name, namesAndBettingMoneys.get(name), initialCards.remove(0))));
    }

    public boolean canGiveNewCardTo(final String name) {
        return values.stream()
                .filter(participant -> participant.hasName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_FOUNDED_EXCEPTION_MESSAGE))
                .canDrawCards();
    }

    public void giveNewCardTo(final String name, final Card newCard) {
        values.stream()
                .filter(participant -> participant.hasName(name))
                .forEach(participant -> participant.draw(newCard));
    }

    public Cards getCardsOf(final String name) {
        return values.stream()
                .filter(participant -> participant.hasName(name))
                .findAny()
                .map(Player::getCards)
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_FOUNDED_EXCEPTION_MESSAGE));
    }

    public void stay(final String name) {
        values.stream()
                .filter(participant -> participant.hasName(name))
                .forEach(Player::stay);
    }

    public double getProfitOf(final String name, final Cards dealerCards) {
        return values.stream()
                .filter(player -> player.hasName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_FOUNDED_EXCEPTION_MESSAGE))
                .getProfitAgainst(dealerCards);
    }
}