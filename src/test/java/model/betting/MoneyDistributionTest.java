package model.betting;

import model.participant.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MoneyDistributionTest {
    private final Map<Name, BettingMoney> moneysOfParticipants = new HashMap<>();
    private final Name brandon = Name.create("Brandon");
    private final Name henry = Name.create("Henry");
    private final Name paul = Name.create("Paul");
    private final Name dealer = Name.create("Dealer");

    @BeforeEach
    void setUp() {
        moneysOfParticipants.put(brandon, BettingMoney.create(30000));
        moneysOfParticipants.put(henry, BettingMoney.create(10000));
        moneysOfParticipants.put(paul, BettingMoney.create(10000));
    }

    @Test
    @DisplayName("게임 결과에 대한 금액을 반환한다.")
    void getDistributedMoneyOfGameThatWinnerIs() {
        MoneyDistribution moneyDistribution = MoneyDistribution.create(moneysOfParticipants);
        Map<Name, Integer> actual = moneyDistribution.getDistributedMoneyOfGameThatWinnerIs(brandon);
        assertAll(
                () -> assertThat(actual).containsEntry(brandon, 30000),
                () -> assertThat(actual).containsEntry(henry, -10000),
                () -> assertThat(actual).containsEntry(paul, -10000),
                () -> assertThat(actual).containsEntry(dealer, -10000)
        );
    }
}