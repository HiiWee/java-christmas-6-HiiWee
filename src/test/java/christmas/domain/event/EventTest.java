package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventTest {

    @DisplayName("이벤트를 참여할 수 있는 최소 금액은 10_000원 이다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            아이스크림-10, true
            아이스크림-1, false
            """)
    void joinEvent_limitPriceIs_10000(String inputMenu, boolean expectCanJoinEvent) {
        // given
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of(inputMenu));
        Reservation reservation = new Reservation(selectedMenus, SelectedDate.createFrom(10));

        // when
        boolean canJoinAnyEvent = Event.canJoinAnyEvent(reservation);

        // then
        assertThat(canJoinAnyEvent).isEqualTo(expectCanJoinEvent);
    }
}