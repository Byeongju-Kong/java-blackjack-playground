package model.betting;

public class BettingMoney {
    private final int value;

    public static BettingMoney create(final int bettingMoney) {
        return new BettingMoney(bettingMoney);
    }

    private BettingMoney(final int bettingMoney) {
        if(bettingMoney <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원 보다 커야합니다.");
        }
        this.value = bettingMoney;
    }

    public int value() {
        return value;
    }
}