package model.betting;

import model.participant.vo.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstBlackJackMoneyDistribution implements MoneyDistribution {
    private final Map<String, BettingMoney> moneysOfParticipants;
    private final Map<String, Integer> finalMoney;

    public static FirstBlackJackMoneyDistribution create(final Map<String, BettingMoney> moneysOfParticipants) {
        return new FirstBlackJackMoneyDistribution(moneysOfParticipants);
    }

    private FirstBlackJackMoneyDistribution(final Map<String, BettingMoney> moneysOfParticipants) {
        this.moneysOfParticipants = moneysOfParticipants;
        finalMoney = new HashMap<>();
    }

    public Map<String, Integer> getDistributedMoneyOfGameThatWinnerIs(final List<Name> winnerNames) {
        giveMoneyToWinners(winnerNames);
        makeDealerPayMoney(winnerNames);
        makeNonWinnersMoneyZero(winnerNames);
        return finalMoney;
    }

    private void giveMoneyToWinners(final List<Name> winnerNames) {
        moneysOfParticipants.keySet().stream()
                .filter(participant -> winnerNames.contains(Name.create(participant)))
                .forEach(winner -> finalMoney.put(winner, (int) (moneysOfParticipants.get(winner).value() * 1.5)));
    }

    private void makeDealerPayMoney(final List<Name> winnerNames) {
        int winnersMoney = moneysOfParticipants.keySet().stream()
                .filter(participant -> winnerNames.contains(Name.create(participant)))
                .mapToInt(winner -> moneysOfParticipants.get(winner).value())
                .sum();
        finalMoney.put("Dealer", (int) (winnersMoney * -1.5));

    }

    private void makeNonWinnersMoneyZero(final List<Name> winnerNames) {
        moneysOfParticipants.keySet().stream()
                .filter(participant -> !winnerNames.contains(Name.create(participant)))
                .forEach(nonWinner -> finalMoney.put(nonWinner, 0));
    }
}