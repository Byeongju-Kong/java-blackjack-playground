package model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @ParameterizedTest
    @DisplayName("트럼프숫자를 반환한다.")
    @MethodSource("providenumberIndexAndTrumpNumber")
    void getNumber(int numberIndex, TrumpNumber expectedTrumpNumber) {
        int anyShapeIndex = 1;
        Card card = Card.generate(numberIndex, anyShapeIndex);
        TrumpNumber actualTrumpNumber = card.getNumber();
        assertThat(actualTrumpNumber).isEqualTo(expectedTrumpNumber);
    }

    private static Stream<Arguments> providenumberIndexAndTrumpNumber() {
        return Stream.of(
                Arguments.of(1, TrumpNumber.A),
                Arguments.of(2, TrumpNumber.TWO),
                Arguments.of(3, TrumpNumber.THREE),
                Arguments.of(4, TrumpNumber.FOUR),
                Arguments.of(5, TrumpNumber.FIVE),
                Arguments.of(6, TrumpNumber.SIX),
                Arguments.of(7, TrumpNumber.SEVEN),
                Arguments.of(8, TrumpNumber.EIGHT),
                Arguments.of(9, TrumpNumber.NINE),
                Arguments.of(10, TrumpNumber.J),
                Arguments.of(11, TrumpNumber.Q),
                Arguments.of(12, TrumpNumber.K)
        );
    }
}