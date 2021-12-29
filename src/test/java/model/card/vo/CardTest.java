package model.card.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static model.card.vo.TrumpNumber.A;
import static model.card.vo.TrumpNumber.THREE;
import static model.card.vo.TrumpShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Test
    @DisplayName("트럼프숫자를 반환한다.")
    void getNumber() {
        Card card = Card.of(THREE, SPADE);
        TrumpNumber actualTrumpNumber = card.getNumber();
        assertThat(actualTrumpNumber).isEqualTo(THREE);
    }

    @Test
    @DisplayName("카드의 정보를 문자열에 담아 반환한다.")
    void toString_provideCardInfo() {
        Card card = Card.of(A, SPADE);
        String actual = card.toString();
        String expected = "A스페이드";
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("카드의 숫자가 A인지 반환한다.")
    @CsvSource({"A, true", "THREE, false"})
    void isA(TrumpNumber trumpNumber, boolean expected) {
        Card card = Card.of(trumpNumber, SPADE);
        boolean actual = card.isA();
        assertThat(actual).isEqualTo(expected);
    }
}