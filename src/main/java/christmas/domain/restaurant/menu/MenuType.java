package christmas.domain.restaurant.menu;

import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.Arrays;
import java.util.List;

public enum MenuType {

    APPETIZER(List.of(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN(List.of(Menu.T_BONE_STEAK, Menu.BARBECUE_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT(List.of(Menu.CHOCO_CAKE, Menu.ICE_CREAM)),
    BEVERAGE(List.of(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE));

    private final List<Menu> menus;

    MenuType(final List<Menu> menus) {
        this.menus = menus;
    }

    public static MenuType findType(final Menu menu) {
        return Arrays.stream(values())
                .filter(menuType -> menuType.contains(menu))
                .findAny()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_MENU_TYPE::create);
    }

    public static boolean isInBeverage(final String menuName) {
        Menu menu = Menu.find(menuName);
        return BEVERAGE.contains(menu);
    }

    public boolean contains(final Menu menu) {
        return menus.contains(menu);
    }
}
