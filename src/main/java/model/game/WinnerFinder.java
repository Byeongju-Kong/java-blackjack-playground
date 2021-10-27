package model.game;

import model.participant.Participant;
import model.participant.vo.Name;

import java.util.List;
import java.util.stream.Collectors;

public class WinnerFinder {
    private final List<Participant> participants;

    public static WinnerFinder create(final List<Participant> participants){
        return new WinnerFinder(participants);
    }

    private WinnerFinder(final List<Participant> participants) {
        this.participants = participants;
    }

    public List<Name> getWinner() {
        if (checkSomeoneHasBlackJack()) {
            return participants.stream()
                    .filter(Participant::hasBlackJackCard)
                    .map(Participant::getName)
                    .collect(Collectors.toUnmodifiableList());
        }
        return findWinnersInNoOneBlackJackCondition();
    }

    public boolean checkSomeoneHasBlackJack() {
        return participants.stream()
                .anyMatch(Participant::hasBlackJackCard);
    }

    private List<Name> findWinnersInNoOneBlackJackCondition() {
        return participants.stream()
                .filter(this::hasHighestCards)
                .map(Participant::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean hasHighestCards(final Participant participant) {
        return participants.stream()
                .filter(otherParticipant -> !otherParticipant.equals(participant))
                .allMatch(participant::hasHigherCardsThan);
    }
}