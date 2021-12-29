package model.card.vo;

public enum TrumpNumber {
    A("A"), TWO("2"), THREE("3"), FOUR("4"),
    FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
    NINE("9"), J("J"), Q("Q"), K("K");

    private final String symbol;

    TrumpNumber(final String symbol) {
        this.symbol = symbol;
    }

    public boolean isA() {
        return this == A;
    }

    public boolean isJQK() {
        return this == J || this == Q || this == K;
    }

    public int value() {
        if (this == J || this == Q || this == K) {
            return 10;
        }
        return Integer.parseInt(symbol);
    }

    @Override
    public String toString() {
        return symbol;
    }
}