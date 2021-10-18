package model.vo;

import java.util.Arrays;

public enum TrumpShape {
    SPADE(1, "스페이드"), DIAMOND(2, "다이아몬드"), HEART(3, "하트"), CLOVER(4, "클로버");
    private final int shapeIndex;
    private final String value;

    TrumpShape(final int shapeIndex, final String value) {
        this.shapeIndex = shapeIndex;
        this.value = value;
    }

    public static TrumpShape findBy(final int shapeIndex) {
        return Arrays.stream(TrumpShape.values())
                .filter(trumpShape -> trumpShape.shapeIndex == shapeIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Index에 대응하는 모양이 없습니다."));
    }

    @Override
    public String toString() {
        return value;
    }
}
