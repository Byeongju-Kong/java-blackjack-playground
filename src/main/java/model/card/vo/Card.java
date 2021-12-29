package model.card.vo;

public class Card {
    private final TrumpNumber number;
    private final TrumpShape shape;

    public static Card of(final TrumpNumber number, final TrumpShape shape) {
        return new Card(number, shape);
    }

    private Card(final TrumpNumber number, final TrumpShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public TrumpNumber getNumber() {
        return number;
    }

    public boolean isA() {
        return number.isA();
    }

    @Override
    public String toString() {
        return number.toString() + shape.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && shape == card.shape;
    }
}