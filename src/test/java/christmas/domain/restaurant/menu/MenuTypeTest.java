package christmas.domain.restaurant.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeTest {

    @DisplayName("메뉴 타입이 음료밖에 없다면 false를 반환한다.")
    @Test
    void allBeverage_return_false() {
        // given
        List<MenuType> menuTypes = List.of(MenuType.BEVERAGE);

        // when
        boolean notOnlyBeverage = MenuType.isNotOnlyBeverage(menuTypes);

        // then
        assertThat(notOnlyBeverage).isFalse();
    }

    @DisplayName("메뉴 타입이 2개 이상이라면 true를 반환한다.")
    @Test
    void overTwoMenuType_return_true() {
        // given
        List<MenuType> menuTypes = List.of(MenuType.BEVERAGE, MenuType.APPETIZER);

        // when
        boolean notOnlyBeverage = MenuType.isNotOnlyBeverage(menuTypes);

        // then
        assertThat(notOnlyBeverage).isTrue();
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