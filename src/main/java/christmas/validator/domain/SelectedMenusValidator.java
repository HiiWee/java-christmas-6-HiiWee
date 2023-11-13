package christmas.validator.domain;

import christmas.domain.restaurant.menu.MenuCondition;
import christmas.domain.restaurant.menu.MenuType;
import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.List;
import java.util.regex.Pattern;


// TODO input 단계에서 할 수 있는 검증 추려내기
public class SelectedMenusValidator {

    private static final Pattern INPUT_MENU_REGEX = Pattern.compile("^[가-힣]+-[1-9][0-9]*$");
    private static final String DELIMITER = "-";
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private SelectedMenusValidator() {
    }

    public static void validateMenus(final List<String> inputMenus) {
        validateFormatAndDuplicates(inputMenus);
        validateLimitMenuCount(inputMenus);
        validateOnlyBeverage(inputMenus);
    }

    private static void validateFormatAndDuplicates(final List<String> inputMenus) {
        if (isInvalidFormat(inputMenus) || isDuplicates(inputMenus)) {
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

    private static boolean isInvalidFormat(final List<String> inputMenus) {
        return !inputMenus.stream()
                .allMatch(inputMenu -> INPUT_MENU_REGEX.matcher(inputMenu).matches());
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
