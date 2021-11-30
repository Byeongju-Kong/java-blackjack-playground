package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
    private final Map<String, Integer> namesAndMoneys = new LinkedHashMap<>();

    {
        namesAndMoneys.put("Chris", 20000);
        namesAndMoneys.put("Brandon", 30000);
        namesAndMoneys.put("Henry", 10000);
    }

    private final Cards cardsOfChris = Cards.from(
            Arrays.asList(Card.of(1, 1), Card.of(10, 3)));
    private final Cards cardsOfBrandon = Cards.from(new ArrayList<>(
            Arrays.asList(Card.of(3, 1), Card.of(11, 2))));
    private final Cards cardsOfHenry = Cards.from(
            Arrays.asList(Card.of(12, 1), Card.of(8, 4)));
    private final List<Cards> initialCards = new ArrayList<>(Arrays.asList(cardsOfChris, cardsOfBrandon, cardsOfHenry));
    private final Players players = Players.of(namesAndMoneys, initialCards);

    @ParameterizedTest
    @DisplayName("문자열에 담긴 이름을 받아, 이름에 해당하는 Player가 새 카드를 뽑을 수 있는지 반환한다.")
    @CsvSource({"Chris, false", "Brandon, true", "Henry, true"})
    void canGiveNewCardTo(String name, boolean expected) {
        boolean actual = players.canGiveNewCardTo(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("giveNewCardTo 메소드로 새로운 카드를 지급하고 getCards 메소드로 카드를 반환받는다.")
    void giveNewCardTo_getCards() {
        players.giveNewCardTo("Brandon", Card.of(5, 4));
        Cards actualBrandonCardsAfterDraw = players.getCardsOf("Brandon");
        Cards expectedBrandonCardsAfterDraw =
                Cards.from(Arrays.asList(Card.of(3, 1), Card.of(11, 2),
                        Card.of(5, 4)));
        assertThat(actualBrandonCardsAfterDraw).isEqualTo(expectedBrandonCardsAfterDraw);
    }

    @Test
    @DisplayName("이름을 받아 이름에 해당하는 Player의 상태를 Stay로 변경한다.")
    void stay() {
        players.stay("Brandon");
        assertThat(players.canGiveNewCardTo("Brandon")).isFalse();
    }

    @ParameterizedTest
    @DisplayName("딜러 카드를 받아 승패를 확인하고 최종 수익을 반환한다.")
    @CsvSource({"Chris, 30000.0", "Brandon, -30000.0", "Henry, 10000.0"})
    void getProfitAgainst(final String name, final double expected) {
        Cards dealerCards = Cards.from(Arrays.asList(Card.of(8, 2), Card.of(9, 4)));
        players.stay("Brandon");
        players.stay("Henry");
        double actual = players.getProfitOf(name, dealerCards);
        assertThat(actual).isEqualTo(expected);
    }
}