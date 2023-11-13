package christmas.domain.event.gift;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.Menu;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChampagneGiftEventTest {

    @DisplayName("120_000원 이상의 메뉴를 구매하면 샴페인을 증정한다.")
    @Test
    void participateEvent() {
        // given
        Reservation reservation = new Reservation(
                SelectedMenus.createFrom(List.of("티본스테이크-3")),
                SelectedDate.createFrom("29")
        );

        // when
        EventJoinHistory history = EventJoinHistory.getInstance();
        GiftEvent champagneGiftEvent = new ChampagneGiftEvent();
        champagneGiftEvent.participateEvent(history, reservation);
        int actualDiscountPrice = history.calculateTotalBenefit();

        // then
        assertThat(actualDiscountPrice).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }
}