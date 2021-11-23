package model.card.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @ParameterizedTest
    @DisplayName("트럼프숫자를 반환한다.")
    @MethodSource("provideNumberIndexAndTrumpNumber")
    void getNumber(int numberIndex, TrumpNumber expectedTrumpNumber) {
        int shapeIndex = 1;
        Card card = Card.of(numberIndex, shapeIndex);
        TrumpNumber actualTrumpNumber = card.getNumber();
        assertThat(actualTrumpNumber).isEqualTo(expectedTrumpNumber);
    }

    private static Stream<Arguments> provideNumberIndexAndTrumpNumber() {
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

    @Test
    @DisplayName("카드의 정보를 문자열에 담아 반환한다.")
    void toString_provideCardInfo() {
        Card card = Card.of(1, 1);
        String actual = card.toString();
        String expected = "A스페이드";
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("카드의 숫자가 A인지 반환한다.")
    @MethodSource("provideNumberIndexAndIsA")
    void isA(int numberIndex, boolean expected) {
        int anyShapeIndex = 1;
        Card card = Card.of(numberIndex, anyShapeIndex);
        boolean actual = card.isA();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNumberIndexAndIsA() {
        int numberIndexOfA = 1;
        int anyNumberIndexOfNotA = 2;

        return Stream.of(
                Arguments.of(numberIndexOfA, true),
                Arguments.of(anyNumberIndexOfNotA, false)
        );
    }
}