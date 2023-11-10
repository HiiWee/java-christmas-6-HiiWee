package christmas.domain.event;

import christmas.domain.menu.MenuType;
import christmas.domain.menu.SelectedMenus;
import java.util.List;

public interface Event {

    static boolean canJoinAnyEvent(final SelectedMenus selectedMenus) {
        List<MenuType> menuTypes = selectedMenus.extractMenuTypes();
        int totalPrice = selectedMenus.calculateTotalPrice();

        // TODO 이벤트 참여 조건 분리
        return MenuType.isNotOnlyBeverage(menuTypes) && totalPrice >= 10_000;
    }
}