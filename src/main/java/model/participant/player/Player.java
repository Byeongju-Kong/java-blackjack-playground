package model.participant.player;

import model.card.vo.Card;
import model.participant.Participant;
import model.participant.player.vo.Name;

import java.util.List;

public class Player extends Participant {
    private final Name name;

    public static Player participate(final String name, final List<Card> initialCards) {
        return new Player(name, initialCards);
    }

    private Player(final String name, final List<Card> initialCards) {
        super(initialCards);
        this.name = Name.create(name);
    }

    public Name getName() {
        return name;
    }
}