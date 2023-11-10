package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.reservation.Reservation;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayEventTest {

    @DisplayName("평일 이벤트는 디저트 메뉴의 개수에 따라 할인액이 결정됩니다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            3, '아이스크림-2,초코케이크-3,시저샐러드-2', 10115
            12, '아이스크림-1,초코케이크-1,바비큐립-3,티본스테이크-3', 4046
            25, '아이스크림-10,초코케이크-5,바비큐립-5', 30345
            """)
    void calculateDiscountPrice_with_weekdayEvent(int date, String inputAllMenu, int expectedDiscountPrice) {
        // given
        List<String> inputMenus = Arrays.stream(inputAllMenu.split(",")).toList();

        // when
        Event weekdayEvent = new WeekdayEvent();
        int discountPrice = weekdayEvent.calculateDiscountPrice(Reservation.createFrom(inputMenus, date));

        // then
        assertThat(discountPrice).isEqualTo(expectedDiscountPrice);
    }

}