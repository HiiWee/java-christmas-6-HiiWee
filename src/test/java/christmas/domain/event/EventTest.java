package christmas.domain.event;

import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @DisplayName("10_000원 이하의 금액일 경우 이벤트를 참여할 수 없다.")
    @Test
    void canNotJoinEvent_lessThan10_000() {
        // given
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of("아이스크림-1", "제로콜라-1"));
        Reservation reservation = new Reservation(selectedMenus, SelectedDate.createFrom("10"));

        // when
        boolean canJoinAnyEvent = Event.canJoinAnyEvent(reservation);

        // then
        Assertions.assertThat(canJoinAnyEvent).isFalse();
    }

    @DisplayName("음료만 있는 경우 이벤트를 참여할 수 없다.")
    @Test
    void canNotJoinEvent_onlyBeverage() {
        // given
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of("레드와인-10", "제로콜라-1"));
        Reservation reservation = new Reservation(selectedMenus, SelectedDate.createFrom("10"));

        // when
        boolean canJoinAnyEvent = Event.canJoinAnyEvent(reservation);

        // then
        Assertions.assertThat(canJoinAnyEvent).isFalse();
    }

    @DisplayName("10_000원 이상 금액에, 메뉴 타입이 음료만 있지 않다면 이벤트에 참여할 수 있다.")
    @Test
    void canJoinAnyEvent() {
        // given
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of("레드와인-10", "제로콜라-1", "아이스크림-1"));
        Reservation reservation = new Reservation(selectedMenus, SelectedDate.createFrom("10"));

        // when
        boolean canJoinAnyEvent = Event.canJoinAnyEvent(reservation);

        // then
        Assertions.assertThat(canJoinAnyEvent).isTrue();
    }
}