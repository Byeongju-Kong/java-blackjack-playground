package model.betting;

import model.participant.vo.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicMoneyDistribution {
    private final Map<Name, BettingMoney> bettingMoneysOfParticipants;
    private final Map<Name, Integer> finalMoney;
    private final int sumOfParticipantsMoney;

    public static BasicMoneyDistribution create(final Map<Name, BettingMoney> moneysOfParticipants) {
        return new BasicMoneyDistribution(moneysOfParticipants);
    }

    private BasicMoneyDistribution(final Map<Name, BettingMoney> moneysOfParticipants) {
        this.bettingMoneysOfParticipants = moneysOfParticipants;
        finalMoney = new HashMap<>();
        sumOfParticipantsMoney = moneysOfParticipants.values().stream()
                .mapToInt(BettingMoney::value)
                .sum();
    }

    public Map<Name, Integer> getDistributedMoneyOfGameThatWinnerIs(final List<Name> winnerNames) {
        distributeMoneyBasedOn(winnerNames);
        return finalMoney;
    }

    private void distributeMoneyBasedOn(final List<Name> winnerNames) {
        int sumOfWinnersMoney = winnerNames.stream()
                .mapToInt(winnerName -> bettingMoneysOfParticipants.get(winnerName).value())
                .sum();
        int sumOfParticipantsMoneyExceptWinners = sumOfParticipantsMoney - sumOfWinnersMoney;
        winnerNames.forEach(winnerName -> finalMoney.put(winnerName, bettingMoneysOfParticipants.get(winnerName).value()));
        bettingMoneysOfParticipants.keySet().stream()
                .filter(participantName -> !winnerNames.contains(participantName))
                .forEach(this::makeMoneyOfLoserMinus);
        finalMoney.put(Name.create("Dealer"), sumOfParticipantsMoneyExceptWinners - sumOfWinnersMoney);
    }

    private void makeMoneyOfLoserMinus(final Name loser) {
        finalMoney.put(loser, bettingMoneysOfParticipants.get(loser).value() * -1);
    }
}