package model.game;

import model.card.CardDeck;
import model.card.Cards;
import model.card.vo.Card;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.vo.Name;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Participant> participants;
    private final CardDeck cardDeck = CardDeck.shuffle();

    public Game(final List<String> names) {
        participants = participate(names);
    }

    List<Participant> participate(final List<String> names) {
        List<Participant> participants = new ArrayList<>();
        names.forEach(name -> participants.add(Participant.participate(name, cardDeck.provideInitialCards())));
        participants.add(Dealer.participate(cardDeck.provideInitialCards()));
        return participants;
    }

    public boolean canGiveNewCardTo(final String name) {
        Participant drawer = participants.stream()
                .filter(participant -> participant.hasName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이름에 맞는 참가자가 없습니다."));
        return drawer.canDrawCards();
    }

    public void giveNewCardTo(final String name) {
        participants.stream()
                .filter(participant -> participant.hasName(name))
                .forEach(participant -> participant.draw(provideNewCard()));
    }

    Card provideNewCard() {
        return cardDeck.provideNewCard();
    }

    public Cards getCardsOf(final String name) {
        return participants.stream()
                .filter(participant -> participant.hasName(name))
                .findAny()
                .map(Participant::getCards)
                .orElseThrow(() -> new IllegalArgumentException("이름에 맞는 참가자가 없습니다."));
    }

    public List<Name> getWinner() {
        WinnerFinder winnerFinder = WinnerFinder.create(participants);
        return winnerFinder.getWinner();
    }

    public boolean checkDealerHasCardsLowerThan16() {
        Dealer dealer = (Dealer) participants.stream()
                .filter(Dealer.class::isInstance)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dealer가 게임에 참가하지 않았습니다."));

        if (dealer.hasCardsLowerThan16()) {
            dealer.draw(cardDeck.provideNewCard());
            return true;
        }
        return false;
    }
}