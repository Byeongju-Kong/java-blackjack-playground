package model.card.vo;

public enum TrumpShape {
    SPADE("스페이드"), DIAMOND("다이아몬드"), HEART("하트"), CLOVER("클로버");
    private final String value;

    TrumpShape(final String value) {
        this.value = value;
    }
}
