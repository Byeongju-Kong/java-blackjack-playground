package model.betting;

import model.participant.player.vo.Name;

import java.util.HashMap;
import java.util.Map;

public class MoneyDistribution {
    private final Map<Name, BettingMoney> moneysOfParticipants;
    private final Map<Name, Integer> finalMoney;
    private final int sumOfParticipantsMoney;

    public MoneyDistribution(final Map<Name, BettingMoney> moneysOfParticipants) {
        this.moneysOfParticipants = moneysOfParticipants;
        finalMoney = new HashMap<>();
        sumOfParticipantsMoney = moneysOfParticipants.values().stream()
                .mapToInt(BettingMoney::value)
                .sum();
    }

    public Map<Name, Integer> getDistributedMoneyOfGameThatWinnerIs(final Name winnerName) {
        distributeMoneyBasedOn(winnerName);
        return finalMoney;
    }

    private void distributeMoneyBasedOn(final Name winnerName) {
        int moneyOfWinner = moneysOfParticipants.get(winnerName).value();
        int sumOfParticipantsMoneyExceptWinner = sumOfParticipantsMoney - moneyOfWinner;
        finalMoney.put(winnerName, moneyOfWinner);
        moneysOfParticipants.keySet().stream()
                .filter(participantName -> !participantName.equals(winnerName))
                .forEach(this::makeMoneyOfLoserMinus);
        finalMoney.put(Name.create("Dealer"), sumOfParticipantsMoneyExceptWinner - moneyOfWinner);
    }

    private void makeMoneyOfLoserMinus(final Name loser) {
        finalMoney.put(loser, moneysOfParticipants.get(loser).value() * -1);
    }
}