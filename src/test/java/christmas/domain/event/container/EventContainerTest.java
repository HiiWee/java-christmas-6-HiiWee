package christmas.domain.event.container;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventContainerTest {

    @DisplayName("이벤트에 참여할 수 있다.")
    @Test
    void joinEvents() {
        // given
        EventContainer eventContainer = EventContainer.getInstance();
        Reservation reservation = new Reservation(SelectedMenus.createFrom(List.of("바비큐립-5", "초코케이크-5")),
                SelectedDate.createFrom(25));
        EventJoinHistory history = EventJoinHistory.getInstance();

        // when
        eventContainer.joinEvents(reservation, history);
        int totalBenefit = history.calculateTotalBenefit();
        int discountBenefit = history.calculateDiscountBenefit();

        // then
        assertAll(
                () -> assertThat(totalBenefit).isEqualTo(39515),
                () -> assertThat(discountBenefit).isEqualTo(14515)
        );
    }
}
