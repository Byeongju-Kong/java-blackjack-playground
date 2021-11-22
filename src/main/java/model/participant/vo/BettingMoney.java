package model.participant.vo;

public class BettingMoney {
    private final int value;

    public static BettingMoney from(int bettingMoney) {
        return new BettingMoney(bettingMoney);
    }

    private BettingMoney(final int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야합니다.");
        }
        this.value = bettingMoney;
    }

    public int value() {
        return value;
    }
}