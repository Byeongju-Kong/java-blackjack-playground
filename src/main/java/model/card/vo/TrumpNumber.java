package model.card.vo;

import java.util.Arrays;

public enum TrumpNumber {
    A(1, "A"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"),
    FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"),
    NINE(9, "9"), J(10, "J"), Q(11, "Q"), K(12, "K");

    private final int numberIndex;
    private final String symbol;

    TrumpNumber(final int numberIndex, final String symbol) {
        this.numberIndex = numberIndex;
        this.symbol = symbol;
    }

    public static TrumpNumber from(final int numberIndex) {
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
        return Math.min(numberIndex, 10);
    }

    @Override
    public String toString() {
        return symbol;
    }
}