package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.date.DateType;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTypeTest {

    @DisplayName("증정 이벤트가 아니라면 거짓을 반환한다.")
    @Test
    void isNotGivingEvent() {
        // given
        EventType christmas = EventType.CHRISTMAS_EVENT;
        EventType giving = EventType.GIVING_EVENT;

        // when
        boolean notGivingEvent = EventType.isNotGivingEvent(christmas);
        boolean givingEvent = EventType.isNotGivingEvent(giving);

        // then
        assertAll(
                () -> assertThat(notGivingEvent).isTrue(),
                () -> assertThat(givingEvent).isFalse()
        );
    }

    @DisplayName("날짜 타입에 따른 이벤트 타입들을 찾을 수 있다.")
    @Test
    void findEventTypeFrom() {
        // given
        List<DateType> dateTypes = List.of(DateType.SPECIAL, DateType.WEEKDAY, DateType.CHRISTMAS);

        // when
        List<EventType> eventTypes = EventType.findEventTypesFrom(dateTypes);

        // then
        assertThat(eventTypes).contains(EventType.SPECIAL_EVENT, EventType.CHRISTMAS_EVENT, EventType.WEEKDAY_EVENT);
    }

    @DisplayName("이벤트에 참여할 수 있다.")
    @Test
    void joinEvents() {
        // given
        Reservation reservation = new Reservation(SelectedMenus.createFrom(List.of("바비큐립-5", "초코케이크-5")),
                SelectedDate.createFrom(25));
        EventJoinHistory history = EventJoinHistory.getInstance();

        // when
        EventType.joinEvents(reservation, history);
        int totalBenefit = history.calculateTotalBenefit();
        int discountBenefit = history.calculateDiscountBenefit();

        // then
        assertAll(
                () -> assertThat(totalBenefit).isEqualTo(39515),
                () -> assertThat(discountBenefit).isEqualTo(14515)
        );
    }

}