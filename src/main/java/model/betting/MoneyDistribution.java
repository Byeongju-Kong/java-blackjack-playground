package model.betting;

import model.participant.vo.Name;

import java.util.List;
import java.util.Map;

public interface MoneyDistribution {
    Map<Name, Integer> getDistributedMoneyOfGameThatWinnerIs(final List<Name> winnerNames);
}
