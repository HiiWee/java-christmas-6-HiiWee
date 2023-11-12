package christmas.domain.restaurant.menu;

import christmas.validator.domain.exception.DomainExceptionMessage;

public record SelectedMenu(Menu menu, int count) {

    private static final int MINIMUM_ORDER_COUNT = 1;

    public static SelectedMenu createFrom(final String menuName, final int count) {
        Menu menu = Menu.find(menuName);
        if (count < MINIMUM_ORDER_COUNT) {
            throw DomainExceptionMessage.INVALID_ORDER.create();
        }
        return new SelectedMenu(menu, count);
    }

    public boolean isSameMenuType(final MenuType targetMenuType) {
        return MenuType.getType(menu) == targetMenuType;
    }

    public int calculateSinglePrice() {
        return menu.price() * count;
    }

    public String getName() {
        return menu.getName();
    }
}
