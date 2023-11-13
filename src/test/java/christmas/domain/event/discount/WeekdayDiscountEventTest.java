package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayDiscountEventTest {

    @DisplayName("평일 이벤트는 디저트 메뉴의 개수에 따라 할인액이 결정됩니다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            3, '아이스크림-2,초코케이크-3,시저샐러드-2', 10115
            12, '아이스크림-1,초코케이크-1,바비큐립-3,티본스테이크-3', 4046
            25, '아이스크림-10,초코케이크-5,바비큐립-5', 30345
            """)
    void participatedEvents_with_weekdayEvent(String date, String inputAllMenu, int expectedDiscountPrice) {
        // given
        List<String> inputMenus = Arrays.stream(inputAllMenu.split(",")).toList();
        SelectedMenus selectedMenus = SelectedMenus.createFrom(inputMenus);
        SelectedDate selectedDate = SelectedDate.createFrom(date);

        // when
        DiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent();
        EventJoinHistory history = EventJoinHistory.getInstance();
        weekdayDiscountEvent.participateEvent(history, new Reservation(selectedMenus, selectedDate));
        int actualDiscountPrice = history.calculateTotalBenefit();

        // then
        assertThat(actualDiscountPrice).isEqualTo(expectedDiscountPrice);
    }

}