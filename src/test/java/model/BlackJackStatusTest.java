package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackStatusTest {
    @ParameterizedTest
    @DisplayName("카드 숫자의 합을 받아 BlackJackStatus를 반환한다.")
    @CsvSource({"21, BLACKJACK", "12, LOWER_THAN_21", "22, BUST"})
    void checkStatus(int sumOfCardValues, BlackJackStatus expected) {
        BlackJackStatus actual = BlackJackStatus.checkStatus(sumOfCardValues);
        assertThat(actual).isEqualTo(expected);
    }

}