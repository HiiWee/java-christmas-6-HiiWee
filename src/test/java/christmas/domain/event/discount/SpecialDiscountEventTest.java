package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.EventParticipationHistory;
import christmas.domain.event.EventType;
import christmas.domain.reservation.Reservation;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountEventTest {

    @DisplayName("특별 이벤트는 전체 주문 금액에서 1000원만 할인합니다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            24, '아이스크림-2,초코케이크-3,시저샐러드-2', 1000
            25, '아이스크림-1,초코케이크-1,바비큐립-3,티본스테이크-3', 1000
            31, '아이스크림-10,초코케이크-5,바비큐립-5', 1000
            """)
    void participatedEvents_with_weekdayEvent(int date, String inputAllMenu, int expectedDiscountPrice) {
        // given
        List<String> inputMenus = Arrays.stream(inputAllMenu.split(",")).toList();

        // when
        DiscountEvent specialDiscountEvent = new SpecialDiscountEvent();
        EventParticipationHistory history = new EventParticipationHistory(new EnumMap<>(EventType.class));
        specialDiscountEvent.participateEvent(history, Reservation.createFrom(inputMenus, date));
        int actualDiscountPrice = history.participatedEvents().get(EventType.SPECIAL_EVENT);

        // then
        assertThat(actualDiscountPrice).isEqualTo(expectedDiscountPrice);
    }

}