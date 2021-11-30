package model.participant.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BettingMoneyTest {
    @ParameterizedTest
    @DisplayName("양수가 아닌 값으로 BettingMoney를 생성하면 예외를 발생시킨다.")
    @ValueSource(ints = {-10000, 0})
    void create_ExceptionByNegativeOrZero(final int wrongBettingMoney) {
        assertThatIllegalArgumentException().isThrownBy(() -> BettingMoney.from(wrongBettingMoney))
                .withMessage("베팅 금액은 0보다 커야합니다.");
    }

    @Test
    @DisplayName("배팅 금액을 반환한다.")
    void value() {
        BettingMoney bettingMoney = BettingMoney.from(10000);
        int actual = bettingMoney.value();
        int expected = 10000;
        assertThat(actual).isEqualTo(expected);
    }
}