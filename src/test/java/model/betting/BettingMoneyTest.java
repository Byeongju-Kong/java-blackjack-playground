package model.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BettingMoneyTest {
    @ParameterizedTest
    @DisplayName("0 이하의 값으로 객체를 생성하면 예외를 발생시킨다")
    @ValueSource(ints = {0, -1000})
    void create_ExceptionByNegativeOrZero(int wrongValue) {
        assertThatIllegalArgumentException().isThrownBy(() -> BettingMoney.create(wrongValue))
                .withMessage("배팅 금액은 0원 보다 커야합니다.");
    }

    @Test
    @DisplayName("금액을 반환한다.")
    void value() {
        BettingMoney bettingMoney = BettingMoney.create(10000);
        int actual = bettingMoney.value();
        int expected = 10000;
        assertThat(actual).isEqualTo(expected);
    }
}