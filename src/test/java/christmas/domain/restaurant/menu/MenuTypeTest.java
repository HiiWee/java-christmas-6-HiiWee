package christmas.domain.restaurant.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTypeTest {

    @DisplayName("메뉴 타입이 음료라면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(textBlock = """
            레드와인, true
            바비큐립, false
            """)
    void allBeverage_return_false(String menuName, boolean expectedResult) {
        // given & when
        boolean notOnlyBeverage = MenuType.isInBeverage(menuName);

        // then
        assertThat(notOnlyBeverage).isEqualTo(expectedResult);
    }

    @DisplayName("메뉴의 타입을 찾을 수 있다.")
    @Test
    void findType() {
        // given
        List<Menu> menus = List.of(Menu.CAESAR_SALAD, Menu.BARBECUE_RIBS, Menu.CHOCO_CAKE, Menu.CHAMPAGNE);

        // when
        List<MenuType> menuTypes = menus.stream()
                .map(MenuType::findType)
                .toList();

        // then
        assertThat(menuTypes).containsExactly(MenuType.APPETIZER, MenuType.MAIN, MenuType.DESSERT, MenuType.BEVERAGE);
    }
}