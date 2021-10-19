package model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TrumpShapeTest {
    @ParameterizedTest
    @DisplayName("Index 값으로 Index에 해당하는 객체를 반환받는다.")
    @MethodSource("ProvideIndexAndTrumpShape")
    void findBy(int index, TrumpShape expectedShape, String expectedValue) {
        TrumpShape actualShape = TrumpShape.indexOf(index);
        String actualValue = expectedShape.toString();
        assertAll(
                () -> assertThat(actualShape).isEqualTo(expectedShape),
                () -> assertThat(actualValue).isEqualTo(expectedValue)
        );
    }

    private static Stream<Arguments> ProvideIndexAndTrumpShape() {
        return Stream.of(
                Arguments.of(1, TrumpShape.SPADE, "스페이드"),
                Arguments.of(2, TrumpShape.DIAMOND, "다이아몬드"),
                Arguments.of(3, TrumpShape.HEART, "하트"),
                Arguments.of(4, TrumpShape.CLOVER, "클로버")
        );
    }
}