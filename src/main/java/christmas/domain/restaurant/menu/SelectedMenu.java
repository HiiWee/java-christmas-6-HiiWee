package christmas.domain.restaurant.menu;

import christmas.validator.domain.exception.DomainExceptionMessage;

public record SelectedMenu(Menu menu, int count) {

    private static final String DELIMITER = "-";
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    public static SelectedMenu createFrom(final String inputMenu) {
        String[] splitMenus = inputMenu.split(DELIMITER);
        validateMenuCount(splitMenus[COUNT_INDEX]);
        return new SelectedMenu(Menu.find(splitMenus[NAME_INDEX]), Integer.parseInt(splitMenus[COUNT_INDEX]));
    }

    private static void validateMenuCount(final String inputCount) {
        if (MenuCondition.isInvalidMenuCount(Integer.parseInt(inputCount))) {
            throw DomainExceptionMessage.INVALID_ORDER.create();
        }
    }

    public boolean isTypeOf(final MenuType compareType) {
        return compareType.contains(menu);
    }

    public int calculateSinglePrice() {
        return menu.price() * count;
    }

    public String getName() {
        return menu.getName();
    }
}
