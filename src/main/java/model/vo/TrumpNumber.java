package model.vo;

import java.util.Arrays;

public enum TrumpNumber {
    A(1, "A"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"),
    SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"),
    J(10, "J"), Q(11, "Q"), K(12, "K");
    private final int numberIndex;
    private final String value;

    TrumpNumber(final int numberIndex, final String value) {
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

    public String value() {
        return value;
    }
}