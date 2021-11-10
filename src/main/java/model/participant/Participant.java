package model.participant;

import model.card.Cards;
import model.card.vo.Card;

public interface Participant {
    void draw(final Card newCards);

    Cards getCards();

    boolean hasName(final String name);

    boolean hasHigherCardsThan(final Player another);

    boolean canDrawCards();

    void stay();
}
