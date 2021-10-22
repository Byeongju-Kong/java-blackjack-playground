package model.participant.player;

import model.card.vo.Card;
import model.participant.Participant;
import model.participant.player.vo.Name;

import java.util.List;

public class Player extends Participant {
    public static Player participate(final String name, final List<Card> initialCards) {
        return new Player(name, initialCards);
    }

    private Player(final String name, final List<Card> initialCards) {
        super(Name.create(name), initialCards);
    }

    public Name getName() {
        return name;
    }
}