package christmas.domain.restaurant.menu;

public enum MenuCondition {

    MAX_RESERVATION_MENU_COUNT(20),
    MIN_EACH_MENU_COUNT(1);

    private final int value;

    MenuCondition(final int value) {
        this.value = value;
    }

    public static boolean isInvalidMenuCount(final int count) {
        return MIN_EACH_MENU_COUNT.value > count;
    }

    public static boolean hasTooManyMenu(final int totalCount) {
        return MAX_RESERVATION_MENU_COUNT.value < totalCount;
    }
}
