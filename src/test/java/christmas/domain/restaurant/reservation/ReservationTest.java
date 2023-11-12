package christmas.domain.restaurant.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.EventType;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.Menu;
import christmas.domain.restaurant.menu.MenuType;
import christmas.domain.restaurant.menu.SelectedMenu;
import christmas.domain.restaurant.menu.SelectedMenus;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReservationTest {

    Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation(
                SelectedMenus.createFrom(List.of("바비큐립-10", "샴페인-5", "초코케이크-5")),
                SelectedDate.createFrom("25")
        );
    }

    @DisplayName("예약한 날짜에 증정 이벤트를 제외한 할인 이벤트 타입이 해당되는지 알 수 있다.")
    @Test
    void containsEventType() {
        // given
        List<EventType> eventTypes = List.of(
                EventType.CHRISTMAS_EVENT,
                EventType.WEEKDAY_EVENT,
                EventType.SPECIAL_EVENT,
                EventType.WEEKEND_EVENT,
                EventType.GIVING_EVENT
        );

        // when
        List<Boolean> results = eventTypes.stream()
                .map(reservation::containsEventType)
                .toList();

        // then
        assertThat(results).containsExactly(true, true, true, false, false);
    }

    @DisplayName("입력한 메뉴 타입과 동일한 타입의 메뉴가 있다면 개수를 전부 계산한다.")
    @Test
    void countSameMenuType() {
        // given & when
        int count = reservation.countSameMenuType(MenuType.MAIN);

        // then
        assertThat(count).isEqualTo(10);
    }

    @DisplayName("예약한 메뉴의 모든 타입을 중복없이 가져온다.")
    @Test
    void getUniqueMenuTypes() {
        // given & when
        List<MenuType> uniqueMenuTypes = reservation.getUniqueMenuTypes();

        // then
        assertThat(uniqueMenuTypes).contains(MenuType.MAIN, MenuType.DESSERT, MenuType.BEVERAGE);
    }

    @DisplayName("예약한 메뉴들을 받아볼 수 있다.")
    @Test
    void getSelectedMenus() {
        // given
        List<SelectedMenu> expectedMenus = List.of(
                SelectedMenu.createFrom("바비큐립-10"),
                SelectedMenu.createFrom("샴페인-5"),
                SelectedMenu.createFrom("초코케이크-5")
        );

        // when
        List<SelectedMenu> selectedMenus = reservation.getSelectedMenus();

        // then
        assertThat(selectedMenus).containsExactlyElementsOf(expectedMenus);
    }

    @DisplayName("예약한 메뉴들의 총 가격을 받아볼 수 있다.")
    @Test
    void getTotalPrice() {
        // given
        int expectedTotalPrice =
                Menu.BARBECUE_RIBS.price() * 10 + Menu.CHAMPAGNE.price() * 5 + Menu.CHOCO_CAKE.price() * 5;

        // when
        int totalPrice = reservation.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }

    @DisplayName("예약한 날짜를 받아볼 수 있다.")
    @Test
    void getReservedDate() {
        // given
        int expectedDate = 25;

        // when
        int date = reservation.getReservedDate();

        // then
        assertThat(date).isEqualTo(expectedDate);
    }

    @DisplayName("입력한 가격보다 크거나 같은 전체 메뉴 가격을 가지고 있다면 참을 반환한다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            739999, true
            740000, true
            740001, false
            """)
    void hasHigherOrSamePrice(int inputPrice, boolean expectedResult) {
        // given & when
        boolean hasHigherOrSamePrice = reservation.hasHigherOrSamePrice(inputPrice);

        // then
        assertThat(hasHigherOrSamePrice).isEqualTo(expectedResult);
    }
}