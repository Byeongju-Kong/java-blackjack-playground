package model.card.vo;

import java.util.Arrays;

public enum TrumpNumber {
    A(1, 1), TWO(2, 2), THREE(3, 3), FOUR(4, 4), FIVE(5, 5),
    SIX(6, 6), SEVEN(7, 7), EIGHT(8, 8), NINE(9, 9),
    J(10, 10), Q(11, 10), K(12, 10);
    private final int numberIndex;
    private final int value;

    TrumpNumber(final int numberIndex, final int value) {
        this.numberIndex = numberIndex;
        this.value = value;
    }

    public static TrumpNumber indexOf(final int numberIndex) {
        return Arrays.stream(TrumpNumber.values())
                .filter(trumpNumber -> trumpNumber.numberIndex == numberIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Index에 대응하는 숫자가 없습니다."));
    }

    public boolean isA() {
        return this == A;
    }

    public boolean isJQK() {
        return this == J || this == Q || this == K;
    }

    public int value() {
        return value;
    }
}