package model.participant.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {
    @Test
    @DisplayName("Blank 상태의 문자열로 객체를 생성하면 예외를 발생시킨다.")
    void create_ExceptionByBlank() {
        assertThatIllegalArgumentException().isThrownBy(() -> Name.create(" "))
                .withMessage("공백으로 이름을 생성할 수 없습니다.");
    }

    @Test
    @DisplayName("이름 값을 반환한다.")
    void value() {
        Name name = Name.create("Brandon");
        String actual = name.value();
        String expected = "Brandon";
        assertThat(actual).isEqualTo(expected);
    }
}