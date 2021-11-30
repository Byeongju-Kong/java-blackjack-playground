package model.card.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrumpNumberTest {
    @ParameterizedTest
    @DisplayName("Index를 값으로 Index에 해당하는 객체를 반환한다.")
    @CsvSource({"1, A", "2, TWO", "3, THREE", "4, FOUR", "5, FIVE", "6, SIX",
            "7, SEVEN", "8, EIGHT", "9, NINE", "10, J", "11, Q", "12, K"})
    void findBy(int index, TrumpNumber expectedTrumpNumber) {
        TrumpNumber actualTrumpNumber = TrumpNumber.from(index);
        assertThat(actualTrumpNumber).isEqualTo(expectedTrumpNumber);
    }

    @ParameterizedTest
    @DisplayName("카드의 합에 사용되는 값을 반환한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void value(int index) {
        TrumpNumber trumpNumber = TrumpNumber.from(index);
        int actualValue = trumpNumber.value();
        int expectedValue = Math.min(index, 10);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @DisplayName("숫자가 A인지 여부를 반환한다.")
    @CsvSource({"1, true", "3, false"})
    void isA(int numberIndex, boolean expected) {
        TrumpNumber trumpNumber = TrumpNumber.from(numberIndex);
        boolean actual = trumpNumber.isA();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("숫자가 J, Q, K 중 하나인지를 반환한다.")
    @CsvSource({"10, true", "11, true", "12, true", "3, false"})
    void isJQK(int numberIndex, boolean expected) {
        TrumpNumber trumpNumber = TrumpNumber.from(numberIndex);
        boolean actual = trumpNumber.isJQK();
        assertThat(actual).isEqualTo(expected);
    }
}