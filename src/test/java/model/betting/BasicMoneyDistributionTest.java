package model.betting;

import model.participant.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BasicMoneyDistributionTest {
    private final Map<String, BettingMoney> moneysOfParticipants = new HashMap<>();

    @BeforeEach
    void setUp() {
        moneysOfParticipants.put("brandon", BettingMoney.create(30000));
        moneysOfParticipants.put("henry", BettingMoney.create(10000));
        moneysOfParticipants.put("paul", BettingMoney.create(10000));
    }

    @Test
    @DisplayName("게임 결과에 대한 금액을 반환한다.")
    void getDistributedMoneyOfGameThatWinnerIs() {
        BasicMoneyDistribution moneyDistribution = BasicMoneyDistribution.create(moneysOfParticipants);
        List<Name> winnerNames = Arrays.asList(Name.create("henry"), Name.create("paul"));
        Map<String, Integer> actual = moneyDistribution.getDistributedMoneyOfGameThatWinnerIs(winnerNames);
        assertAll(
                () -> assertThat(actual).containsEntry("brandon", -30000),
                () -> assertThat(actual).containsEntry("henry", 10000),
                () -> assertThat(actual).containsEntry("paul", 10000),
                () -> assertThat(actual).containsEntry("Dealer", 10000)
        );
    }
}