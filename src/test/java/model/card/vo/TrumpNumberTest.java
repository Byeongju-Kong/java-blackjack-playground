package model.card.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrumpNumberTest {
    @ParameterizedTest
    @DisplayName("숫자가 A인지 여부를 반환한다.")
    @CsvSource({"A, true", "THREE, false"})
    void isA(TrumpNumber trumpNumber, boolean expected) {
        boolean actual = trumpNumber.isA();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("숫자가 J, Q, K 중 하나인지를 반환한다.")
    @CsvSource({"J, true", "Q, true", "J, true", "THREE, false"})
    void isJQK(TrumpNumber trumpNumber, boolean expected) {
        boolean actual = trumpNumber.isJQK();
        assertThat(actual).isEqualTo(expected);
    }
}