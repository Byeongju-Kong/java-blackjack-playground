package model.participant.player;

import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    private final List<Card> initialCards =
            new ArrayList<>(Arrays.asList(Card.generate(8, 1), Card.generate(9, 1)));

    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getName() {
        Player player = Player.participate("Brandon", initialCards);
        Name actual = player.getName();
        Name expected = Name.create("Brandon");
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어의 숫자가 21이 넘어 패배하였는지에 대한 여부를 알려준다.")
    @MethodSource("provideCardsAndIsDefeater")
    void isDefeater(Card newCard, boolean expected) {
        Player player = Player.participate("Brandon", initialCards);
        player.draw(newCard);
        boolean actual = player.isDefeater();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndIsDefeater() {
        return Stream.of(
                Arguments.of(Card.generate(5, 1), true),
                Arguments.of(Card.generate(4, 1), false)
        );
    }

    @ParameterizedTest
    @DisplayName("플레이어의 카드가 블랙잭인지 반환한다.")
    @MethodSource("provideCardsAndHasBlackJackCards")
    void hasBlackJackCards(List<Card> initialCards, boolean expected) {
        Player player = Player.participate("Brandon", initialCards);
        boolean actual = player.hasBlackJackCard();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndHasBlackJackCards() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.generate(10, 1), Card.generate(11, 1)),
                        false),
                Arguments.of(Arrays.asList(Card.generate(1, 1), Card.generate(10, 1)),
                        true)
        );
    }
}