package model.betting;

import model.participant.vo.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicMoneyDistribution implements MoneyDistribution {
    private final Map<String, BettingMoney> bettingMoneysOfParticipants;
    private final Map<String, Integer> finalMoney;
    private final int sumOfParticipantsMoney;

    public static BasicMoneyDistribution create(final Map<String, BettingMoney> moneysOfParticipants) {
        return new BasicMoneyDistribution(moneysOfParticipants);
    }

    private BasicMoneyDistribution(final Map<String, BettingMoney> moneysOfParticipants) {
        this.bettingMoneysOfParticipants = moneysOfParticipants;
        finalMoney = new HashMap<>();
        sumOfParticipantsMoney = moneysOfParticipants.values().stream()
                .mapToInt(BettingMoney::value)
                .sum();
    }

    public Map<String, Integer> getDistributedMoneyOfGameThatWinnerIs(final List<Name> winnerNames) {
        distributeMoneyBasedOn(winnerNames);
        return finalMoney;
    }

    private void distributeMoneyBasedOn(final List<Name> winnerNames) {
        int sumOfWinnersMoney = winnerNames.stream()
                .mapToInt(winnerName -> bettingMoneysOfParticipants.get(winnerName.value()).value())
                .sum();
        int sumOfParticipantsMoneyExceptWinners = sumOfParticipantsMoney - sumOfWinnersMoney;
        winnerNames.forEach(winnerName -> finalMoney.put(winnerName.value(), bettingMoneysOfParticipants.get(winnerName.value()).value()));
        bettingMoneysOfParticipants.keySet().stream()
                .filter(participantName -> !winnerNames.contains(Name.create(participantName)))
                .forEach(this::makeMoneyOfLoserMinus);
        finalMoney.put("Dealer", sumOfParticipantsMoneyExceptWinners - sumOfWinnersMoney);
    }

    private void makeMoneyOfLoserMinus(final String loser) {
        finalMoney.put(loser, bettingMoneysOfParticipants.get(loser).value() * -1);
    }
}