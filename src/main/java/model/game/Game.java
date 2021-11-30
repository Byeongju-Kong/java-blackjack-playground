package model.game;

import model.card.CardDeck;
import model.card.Cards;
import model.participant.Dealer;
import model.participant.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Game {
    private static final int MULTIPLICATION_OF_DEALER_PROFIT = -1;
    private final Players players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public Game(final Map<String, Integer> namesAndBettingMoneys, final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        List<Cards> initialPlayerCars = new ArrayList<>();
        IntStream.range(0, namesAndBettingMoneys.size())
                .forEach(index -> initialPlayerCars.add(cardDeck.provideInitialCards()));
        players = Players.of(namesAndBettingMoneys, initialPlayerCars);
        dealer = Dealer.from(cardDeck.provideInitialCards());
    }

    public boolean canGiveNewCardTo(final String name) {
        if (isDealer(name)) {
            return dealer.canDrawCards();
        }
        return players.canGiveNewCardTo(name);
    }

    public void giveNewCardTo(final String name) {
        if (isDealer(name)) {
            dealer.draw(cardDeck.provideNewCard());
        }
        players.giveNewCardTo(name, cardDeck.provideNewCard());
    }

    public Cards getCardsOf(final String name) {
        if (name.equals("Dealer")) {
            return dealer.getCards();
        }
        return players.getCardsOf(name);
    }

    private boolean isDealer(final String name) {
        return name.equals("Dealer");
    }

    public void stay(final String name) {
        players.stay(name);
    }

    public double getProfitOf(final String name) {
        if (isDealer(name)) {
            return players.getSumOfPlayersProfit(dealer.getCards()) * MULTIPLICATION_OF_DEALER_PROFIT;
        }
        return players.getProfitOf(name, dealer.getCards());
    }
}
