package model.card.vo;

public class Card {
    private final TrumpNumber number;
    private final TrumpShape shape;

    public static Card of(final int numberIndex, final int shapeIndex) {
        return new Card(numberIndex, shapeIndex);
    }

    private Card(final int numberIndex, final int shapeIndex) {
        this.shape = TrumpShape.from(shapeIndex);
        this.number = TrumpNumber.from(numberIndex);
    }

    public TrumpNumber getNumber() {
        return number;
    }

    public boolean isA() {
        return number.isA();
    }

    @Override
    public String toString() {
        return number + shape.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && shape == card.shape;
    }
}