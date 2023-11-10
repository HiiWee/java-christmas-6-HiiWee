package christmas.domain.event.gift;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.EventParticipationHistory;
import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;
import christmas.domain.reservation.Reservation;
import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChampagneGiftEventTest {

    @DisplayName("120_000원 이상의 메뉴를 구매하면 샴페인을 증정한다.")
    @Test
    void participateEvent() {
        // given
        Reservation reservation = Reservation.createFrom(List.of("티본스테이크-3"), 29);

        // when
        EventParticipationHistory history = new EventParticipationHistory(new EnumMap<>(EventType.class));
        GiftEvent champagneGiftEvent = new ChampagneGiftEvent();
        champagneGiftEvent.participateEvent(history, reservation);
        int actualDiscountPrice = history.participatedEvents().get(EventType.GIVING_EVENT);

        // then
        assertThat(actualDiscountPrice).isEqualTo(Menu.CHAMPAGNE.price());
    }
}