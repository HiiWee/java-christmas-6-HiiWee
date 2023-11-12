package christmas.validator.domain;

import christmas.domain.restaurant.menu.MenuCondition;
import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.List;
import java.util.regex.Pattern;

public class SelectedMenusValidator {

    private static final Pattern INPUT_MENU_REGEX = Pattern.compile("^[가-힣]+-[1-9][0-9]*$");
    private static final String DELIMITER = "-";
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private SelectedMenusValidator() {
    }

    public static void validateMenus(final List<String> inputMenus) {
        if (isInvalidFormat(inputMenus) || isDuplicates(inputMenus) || hasTooManyMenu(inputMenus)) {
            throw DomainExceptionMessage.INVALID_ORDER.create();
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
}
