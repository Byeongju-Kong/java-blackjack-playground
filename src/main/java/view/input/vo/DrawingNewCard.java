package view.input.vo;

import java.util.Arrays;

public enum DrawingNewCard {
    YES('y'),
    NO('n');

    private final char value;

    DrawingNewCard(final char value) {
        this.value = value;
    }

    public static DrawingNewCard of(final char value) {
        return Arrays.stream(values())
                .filter(drawingNewCard -> drawingNewCard.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("추가 카드에 대한 입력은 y 혹은 n입니다."));
    }
}
