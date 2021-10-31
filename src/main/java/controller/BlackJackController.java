package controller;

import model.betting.BasicMoneyDistribution;
import model.betting.BettingMoney;
import model.betting.FirstBlackJackMoneyDistribution;
import model.betting.MoneyDistribution;
import model.game.Game;
import model.participant.vo.Name;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {
    private final Game game;
    private MoneyDistribution moneyDistribution;
    private final InputView inputView;
    private final OutputView outputView;
    private final List<String> participantNames;
    private final Map<String, BettingMoney> bettingMoneysOfParticipants = new HashMap<>();

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        participantNames = Arrays.asList(inputView.inputPlayerNames());
        inputBettingMoneys();
        moneyDistribution = FirstBlackJackMoneyDistribution.create(bettingMoneysOfParticipants);
        game = new Game(participantNames);
    }

    public void run() {
        outputView.showGameStart(participantNames);
        participantNames.forEach(name -> outputView.showCardsOf(name, game.getCardsOf(name)));
        List<Name> winnerNames = playGamesAndGetWinners();
        participantNames.forEach(name -> outputView.showFinalResultOf(name, game.getCardsOf(name)));
        outputView.showFinalResultOf("Dealer", game.getCardsOf("Dealer"));
        outputView.showFinalMoney(moneyDistribution.getDistributedMoneyOfGameThatWinnerIs(winnerNames));
    }

    private void inputBettingMoneys() {
        participantNames
                .forEach(name -> bettingMoneysOfParticipants.put(name, BettingMoney.create(inputView.inputBettingMoneyOf(name))));
    }

    private List<Name> playGamesAndGetWinners() {
        if (checkFirstCardsBlackJack()) {
            return game.getWinner();
        }
        moneyDistribution = BasicMoneyDistribution.create(bettingMoneysOfParticipants);
        participantNames.forEach(this::playTurnOf);
        outputView.alertNewCardOfDealer(game.checkDealerHasCardsLowerThan16());
        return game.getWinner();
    }

    private boolean checkFirstCardsBlackJack() {
        return participantNames.stream()
                .anyMatch(name -> !game.canGiveNewCardTo(name));
    }

    private void playTurnOf(final String name) {
        while (game.canGiveNewCardTo(name) && inputView.inputDrawingNewCard(name) == 'y') {
            game.giveNewCardTo(name);
            outputView.showCardsOf(name, game.getCardsOf(name));
        }
    }
}
