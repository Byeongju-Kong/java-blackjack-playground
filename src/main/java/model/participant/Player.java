package model.participant;

import model.card.Cards;
import model.participant.vo.BettingMoney;
import model.participant.vo.Name;
import model.state.finished.BlackJack;
import model.state.finished.Stay;
import model.state.running.Hit;

import java.util.Objects;

public class Player extends Participant{
    private final Name name;
    private final BettingMoney bettingMoney;

    public static Player of(final String name, final int bettingMoney, final Cards initialCards) {
        return new Player(name, bettingMoney, initialCards);
    }

    private Player(final String name, final int bettingMoney, final Cards initialCards) {
        if (initialCards.hasSumOf21ComposedWithTwoCard()) {
            this.state = new BlackJack(initialCards);
        } else if (!initialCards.hasSumOf21ComposedWithTwoCard()) {
            this.state = new Hit(initialCards);
        }
        this.bettingMoney = BettingMoney.from(bettingMoney);
        this.name = Name.create(name);
    }

    public boolean hasName(final String name) {
        return this.name.value().equals(name);
    }

    @Override
    public boolean canDrawCards() {
        return !state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }

    public void checkFinalResult(Cards dealerCards) {
        if (state.getClass().equals(Stay.class) && getCards().hasHigherSumOfCardValuesThan(dealerCards)) {
            state = state.win();
        } else if (state.getClass().equals(Stay.class) && !getCards().hasHigherSumOfCardValuesThan(dealerCards)) {
            state = state.lose();
        }
    }

    public double getProfit() {
        return state.profit(bettingMoney.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}