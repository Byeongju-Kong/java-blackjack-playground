package model.game;

import model.card.vo.Card;
import model.participant.Participant;
import model.participant.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WinnerFinderTest {
    List<Card> cards = Arrays.asList(
            Card.generate(1, 1), Card.generate(10, 2),
            Card.generate(1, 2), Card.generate(2, 3),
            Card.generate(2, 3), Card.generate(5, 3));
    private int cardIndex = 0;
    List<String> names = Arrays.asList("Brandon", "Henry");

    @Test
    @DisplayName("우승자를 반환한다.")
    void getWinnerOf() {
        List<Participant> participants = new ArrayList<>();
        names.forEach(name -> participants.add(Participant.participate(name, provideInitialCards())));
        participants.add(Participant.participate("Dealer", provideInitialCards()));
        WinnerFinder winnerFinder = WinnerFinder.create(participants);
        List<Name> winners = winnerFinder.getWinner();
        assertAll(
                () -> assertThat(winners.get(0)).isEqualTo(Name.create("Brandon")),
                () -> assertThat(winners.size()).isEqualTo(1)
        );
    }

    private List<Card> provideInitialCards() {
        return new ArrayList<>(Arrays.asList(cards.get(cardIndex++), cards.get(cardIndex++)));
    }
}