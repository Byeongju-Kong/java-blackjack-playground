package model.game;

import model.card.CardDeck;
import model.participant.Participant;
import model.participant.vo.Name;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final List<Participant> participants;
    private final CardDeck cardDeck = CardDeck.shuffle();

    public Game(final String[] names) {
        participants = participate(names);
    }

    protected List<Participant> participate(final String[] names) {
        List<Participant> participants = new ArrayList<>();
        Arrays.stream(names)
                .forEach(name -> participants.add(Participant.participate(name, cardDeck.provideInitialCards())));
        participants.add(Participant.participate("Dealer", cardDeck.provideInitialCards()));
        return participants;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<Name> getWinner() {
        WinnerFinder winnerFinder = WinnerFinder.create(participants);
        return winnerFinder.getWinner();
    }

    public boolean checkDealerHasCardsLowerThan16() {
        Participant dealer = participants.stream()
                .filter(participant -> participant.getName().value().equals("Dealer"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dealer가 게임에 참가하지 않았습니다."));
        if (dealer.hasCardsLowerThan16()) {
            dealer.draw(cardDeck.provideNewCard());
            return true;
        }
        return false;
    }
}