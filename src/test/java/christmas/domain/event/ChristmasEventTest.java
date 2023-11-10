package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.reservation.Reservation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasEventTest {

    @DisplayName("크리스마스 이벤트에 참여하여 할인을 받을 수 있다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            24, 3300
            23, 3200
            25, 3400
            2, 1100
            1, 1000
            """)
    void calculateDiscountPrice_with_christmasEvent(int date, int expectedDiscountPrice) {
        // given
        Reservation reservation = Reservation.createFrom(List.of("양송이수프-1", "타파스-2"), date);

        // when
        Event event = new ChristmasEvent();
        int discountPrice = event.calculateDiscountPrice(reservation);

        // then
        assertThat(discountPrice).isEqualTo(expectedDiscountPrice);
    }

}