package model.card.vo;

public enum TrumpNumber {
    A(1), TWO(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), J(10), Q(10), K(10);

    private final int value;

    TrumpNumber(final int value) {
        this.value = value;
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