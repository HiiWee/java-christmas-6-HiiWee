package christmas.domain.restaurant.menu;

import christmas.validator.domain.exception.DomainExceptionMessage;

public record SelectedMenu(Menu menu, int count) {

    private static final String DELIMITER = "-";
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    // TODO 11.12작성: 좀 더 깔끔하게 로직을 만들 수 없을까?
    public static SelectedMenu createFrom(final String inputMenu) {
        String[] splitMenus = inputMenu.split(DELIMITER);
        int count = Integer.parseInt(splitMenus[COUNT_INDEX]);

        validateMenuCount(count);
        Menu menu = Menu.find(splitMenus[NAME_INDEX]);
        return new SelectedMenu(menu, count);
    }

    private static void validateMenuCount(final int count) {
        if (MenuCondition.isInvalidMenuCount(count)) {
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
