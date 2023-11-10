package christmas.domain.menu;

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
}
