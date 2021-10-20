package model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TrumpNumberTest {
    @ParameterizedTest
    @DisplayName("Index를 값으로 Index에 해당하는 객체를 반환받는다.")
    @MethodSource("provideIndexAndTrumpNumber")
    void findBy(int index, TrumpNumber expectedTrumpNumber, String expectedValue) {
        TrumpNumber actualTrumpNumber = TrumpNumber.indexOf(index);
        String actualValue = actualTrumpNumber.value();
        assertAll(
                () -> assertThat(actualTrumpNumber).isEqualTo(expectedTrumpNumber),
                () -> assertThat(actualValue).isEqualTo(expectedValue)
        );
    }

    private static Stream<Arguments> provideIndexAndTrumpNumber() {
        return Stream.of(
                Arguments.of(1, TrumpNumber.A, "A"),
                Arguments.of(2, TrumpNumber.TWO, "2"),
                Arguments.of(3, TrumpNumber.THREE, "3"),
                Arguments.of(4, TrumpNumber.FOUR, "4"),
                Arguments.of(5, TrumpNumber.FIVE, "5"),
                Arguments.of(6, TrumpNumber.SIX, "6"),
                Arguments.of(7, TrumpNumber.SEVEN, "7"),
                Arguments.of(8, TrumpNumber.EIGHT, "8"),
                Arguments.of(9, TrumpNumber.NINE, "9"),
                Arguments.of(10, TrumpNumber.J, "J"),
                Arguments.of(11, TrumpNumber.Q, "Q"),
                Arguments.of(12, TrumpNumber.K, "K")
        );
    }

    @Test
    @DisplayName("숫자가 A인지 여부를 반환한다.")
    void isA() {
        TrumpNumber trumpNumber = TrumpNumber.indexOf(1);
        boolean actual = trumpNumber.isA();
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("숫자가 J, Q, K 중 하나인지를 반환한다.")
    @CsvSource({"10, true", "11, true", "12, true", "3, false"})
    void isJQK(int numberIndex, boolean expected) {
        TrumpNumber trumpNumber = TrumpNumber.indexOf(numberIndex);
        boolean actual = trumpNumber.isJQK();
        assertThat(actual).isEqualTo(expected);
    }
}