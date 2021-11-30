package controller;

import model.card.CardDeck;
import model.game.Game;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {
    private static final String REFERENCE_VALUE_OF_DEALER = "Dealer";
    private final Game game;
    private final InputView inputView;
    private final OutputView outputView;
    private final List<String> participantNames;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        participantNames = Arrays.asList(inputView.inputPlayerNames());
        game = setForGame();
    }

    private Game setForGame() {
        CardDeck cardDeck = new CardDeck();
        Map<String, Integer> namesAndBettingMoney = new LinkedHashMap<>();
        participantNames.forEach(name -> namesAndBettingMoney.put(name, inputView.inputBettingMoneyOf(name)));
        outputView.showGameStart(participantNames);
        return new Game(namesAndBettingMoney, cardDeck);
    }

    public void run() {
        showInitialCards();
        playGame();
        showResult();
        showProfits();
    }

    private void showInitialCards() {
        outputView.showOneOfInitialCardsOf("딜러", game.getCardsOf(REFERENCE_VALUE_OF_DEALER).getCards().get(0));
        participantNames.forEach(name -> outputView.showCardsOf(name, game.getCardsOf(name)));
    }

    private void playGame() {
        participantNames.forEach(this::playTurnOf);
        outputView.alertNewCardOfDealer(game.canGiveNewCardTo(REFERENCE_VALUE_OF_DEALER));
    }


    private void playTurnOf(final String name) {
        while (game.canGiveNewCardTo(name) && inputView.inputDrawingNewCard(name) == 'y') {
            game.giveNewCardTo(name);
            outputView.showCardsOf(name, game.getCardsOf(name));
        }
        if (game.canGiveNewCardTo(name)) {
            game.stay(name);
        }
    }

    private void showResult() {
        outputView.showFinalResultOf("딜러", game.getCardsOf(REFERENCE_VALUE_OF_DEALER));
        participantNames.forEach(name -> outputView.showFinalResultOf(name, game.getCardsOf(name)));
    }

    private void showProfits() {
        outputView.showFinalMoney(game.getProfits());
    }
}
