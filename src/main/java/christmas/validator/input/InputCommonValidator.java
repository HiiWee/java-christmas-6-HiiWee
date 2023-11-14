package christmas.validator.input;

import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.List;
import java.util.regex.Pattern;

public class InputCommonValidator {

    private static final Pattern INPUT_MENU_REGEX = Pattern.compile("^[가-힣]+-[1-9][0-9]*$");

    private InputCommonValidator() {
    }

    public static void validateDate(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw DomainExceptionMessage.INVALID_INPUT_DATE.create();
        }
    }

    public static void validateInputMenus(final List<String> inputMenus) {
        if (isInvalidFormat(inputMenus)) {
            throw DomainExceptionMessage.INVALID_ORDER.create();
        }
    }

    private static boolean isInvalidFormat(final List<String> inputMenus) {
        return !inputMenus.stream()
                .allMatch(inputMenu -> INPUT_MENU_REGEX.matcher(inputMenu).matches());
    }
}
