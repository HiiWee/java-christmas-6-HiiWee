package christmas.domain.restaurant.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SelectedMenusTest {

    @DisplayName("올바르지 않은 메뉴 입력이라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립=3", "크리스마스파스타-0", "123-1"})
    void createSelectedMenus_exception_invalidInputFormat(String invalidMenu) {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedMenus.createFrom(List.of(invalidMenu)))
                .withMessageContaining(DomainExceptionMessage.INVALID_ORDER.message());
    }

    @DisplayName("중복된 메뉴이름을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립-3,크리스마스파스타-1,크리스마스파스타-1"})
    void createSelectedMenus_exception_duplicatesInputFormat(String invalidMenus) {
        // given when
        List<String> menus = Arrays.stream(invalidMenus.split(",")).toList();

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedMenus.createFrom(menus))
                .withMessageContaining(DomainExceptionMessage.INVALID_ORDER.message());
    }

    @DisplayName("주문하는 메뉴 수량이 총 20개를 초과하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립-2,크리스마스파스타-20"})
    void createSelectedMenus_exception_smallCount(String invalidMenus) {
        // given when
        List<String> menus = Arrays.stream(invalidMenus.split(",")).toList();

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedMenus.createFrom(menus))
                .withMessageContaining(DomainExceptionMessage.TOO_MANY_RESERVATION_MENU.message());
    }

    @DisplayName("포함되어 있는 모든 메뉴의 타입을 중복 없이 가져올 수 있다.")
    @Test
    void extractMenuTypes() {
        // given
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of("초코케이크-1", "샴페인-10", "바비큐립-5"));

        // when
        List<MenuType> menuTypes = selectedMenus.extractUniqueMenuTypes();

        // then
        assertThat(menuTypes).contains(MenuType.DESSERT, MenuType.BEVERAGE, MenuType.MAIN);
    }

    @DisplayName("선택한 메뉴들의 총 가격을 계산할 수 있다.")
    @Test
    void calculateTotalPrice() {
        // given
        int expectedTotalPrice =
                Menu.CHOCO_CAKE.getPrice() + Menu.CHAMPAGNE.getPrice() * 10 + Menu.BARBECUE_RIBS.getPrice() * 5;
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of("초코케이크-1", "샴페인-10", "바비큐립-5"));

        // when
        int totalPrice = selectedMenus.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }
}