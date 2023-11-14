package christmas.validator.domain;

import christmas.domain.restaurant.menu.MenuCondition;
import christmas.domain.restaurant.menu.MenuType;
import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.List;

public class SelectedMenusValidator {

    private static final String DELIMITER = "-";
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private SelectedMenusValidator() {
    }

    public static void validateMenus(final List<String> inputMenus) {
        validateDuplicates(inputMenus);
        validateLimitMenuCount(inputMenus);
        validateOnlyBeverage(inputMenus);
    }

    private static void validateDuplicates(final List<String> inputMenus) {
        if (isDuplicates(inputMenus)) {
            throw DomainExceptionMessage.INVALID_ORDER.create();
        }
    }

    private static void validateLimitMenuCount(final List<String> inputMenus) {
        if (hasTooManyMenu(inputMenus)) {
            throw DomainExceptionMessage.TOO_MANY_RESERVATION_MENU.create();
        }
    }

    private static void validateOnlyBeverage(final List<String> inputMenus) {
        if (isAllBeverage(inputMenus)) {
            throw DomainExceptionMessage.CAN_NOT_RESERVE_ONLY_BEVERAGE.create();
        }
    }

    private static boolean isDuplicates(final List<String> inputMenus) {
        return inputMenus.stream()
                .map(inputMenu -> inputMenu.split(DELIMITER)[NAME_INDEX])
                .distinct()
                .count() != inputMenus.size();
    }

    private static boolean hasTooManyMenu(final List<String> inputMenus) {
        return MenuCondition.hasTooManyMenu(inputMenus.stream()
                .mapToInt(inputMenu -> Integer.parseInt(inputMenu.split(DELIMITER)[COUNT_INDEX]))
                .sum());
    }

    private static boolean isAllBeverage(final List<String> inputMenus) {
        return inputMenus.stream()
                .map(input -> input.split(DELIMITER)[NAME_INDEX])
                .allMatch(MenuType::isInBeverage);
    }
}
