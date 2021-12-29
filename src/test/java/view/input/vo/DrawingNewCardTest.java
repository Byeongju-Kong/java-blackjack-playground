package view.input.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DrawingNewCardTest {
    @Test
    @DisplayName("y 혹은 n을 제외한 값으로 객체를 생성하려하면 예외를 발생시킨다.")
    void evokeExceptionByWrongInput() {
        char wrongInputValue = 'o';
        assertThatIllegalArgumentException().isThrownBy(() -> DrawingNewCard.of(wrongInputValue))
                .withMessage("추가 카드에 대한 입력은 y 혹은 n입니다.");
    }
}