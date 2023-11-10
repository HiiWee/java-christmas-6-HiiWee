package christmas.domain.menu;

import java.util.List;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(notOnlyBeverage).isFalse();
    }

    @DisplayName("메뉴 타입이 2개 이상이라면 true를 반환한다.")
    @Test
    void overTwoMenuType_return_true() {
        // given
        List<MenuType> menuTypes = List.of(MenuType.BEVERAGE, MenuType.APPETIZER);

        // when
        boolean notOnlyBeverage = MenuType.isNotOnlyBeverage(menuTypes);

        // then
        Assertions.assertThat(notOnlyBeverage).isTrue();
    }

}