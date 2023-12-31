package christmas.domain.event.specification;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.history.EventJoinHistory;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDiscountEventTest {

    @DisplayName("크리스마스 이벤트에 참여하여 할인을 받을 수 있다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            24, 3300
            23, 3200
            25, 3400
            2, 1100
            1, 1000
            """)
    void participatedEvents_with_christmasEvent(int date, int expectedDiscountPrice) {
        // given
        Reservation reservation = new Reservation(
                SelectedMenus.createFrom(List.of("양송이수프-1", "타파스-2")),
                SelectedDate.createFrom(date)
        );

        // when
        Event discountEvent = new ChristmasDiscountEvent();
        EventJoinHistory history = EventJoinHistory.getInstance();
        discountEvent.participateEvent(history, reservation);
        System.out.println(history);
        int actualDiscountPrice = history.calculateTotalBenefit();

        // then
        assertThat(actualDiscountPrice).isEqualTo(expectedDiscountPrice);
    }

}