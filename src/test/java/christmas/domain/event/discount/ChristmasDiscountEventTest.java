package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.date.SelectedDate;
import christmas.domain.event.EventType;
import christmas.domain.event.history.EventParticipationHistory;
import christmas.domain.menu.SelectedMenus;
import christmas.domain.reservation.Reservation;
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
    void participatedEvents_with_christmasEvent(String date, int expectedDiscountPrice) {
        // given
        Reservation reservation = new Reservation(
                SelectedMenus.createFrom(List.of("양송이수프-1", "타파스-2")),
                SelectedDate.createFrom(date)
        );

        // when
        DiscountEvent discountEvent = new ChristmasDiscountEvent();
        EventParticipationHistory history = EventParticipationHistory.getInstance();
        discountEvent.participateEvent(history, reservation);
        System.out.println(history);
        int actualDiscountPrice = history.benefitPrices().events().get(EventType.CHRISTMAS_EVENT);

        // then
        assertThat(actualDiscountPrice).isEqualTo(expectedDiscountPrice);
    }

}