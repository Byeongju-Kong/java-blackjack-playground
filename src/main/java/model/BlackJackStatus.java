package model;

public enum BlackJackStatus {
    BLACKJACK,
    LOWER_THAN_21,
    BUST;

    public static BlackJackStatus checkStatus(int sumOfCardValues) {
        if(sumOfCardValues == 21) {
            return BLACKJACK;
        }
        if(sumOfCardValues > 21) {
            return BUST;
        }
        return LOWER_THAN_21;
    }
}