package christmas.domain.menu;

import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public record SelectedMenus(List<SelectedMenu> menus) {

    // TODO 검증 분리
    private static final Pattern INPUT_MENU_REGEX = Pattern.compile("^[가-힣]+-[1-9][0-9]*$");
    private static final String DELIMITER = "-";
    // TODO 조건 분리
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final int LIMIT_MENU_COUNT = 20;

    public static SelectedMenus createFrom(List<String> inputMenus) {
        validate(inputMenus);
        return new SelectedMenus(inputMenus.stream()
                .map(inputMenu -> inputMenu.split(DELIMITER))
                .map(splitMenu -> SelectedMenu.createFrom(splitMenu[NAME_INDEX],
                        Integer.parseInt(splitMenu[COUNT_INDEX])))
                .toList());
    }

    @Override
    public List<SelectedMenu> menus() {
        return Collections.unmodifiableList(menus);
    }

    public List<MenuType> extractMenuTypes() {
        return menus.stream()
                .map(SelectedMenu::menu)
                .map(MenuType::getType)
                .distinct()
                .toList();
    }

    public int calculateTotalPrice() {
        return menus.stream()
                .mapToInt(SelectedMenu::calculateSinglePrice)
                .sum();
    }

    // TODO 검증문 리팩토링 하기
    private static void validate(final List<String> inputMenus) {
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
                .map(inputMenu -> inputMenu.split(DELIMITER))
                .map(splitMenu -> splitMenu[NAME_INDEX])
                .distinct()
                .count() != inputMenus.size();
    }

    private static boolean hasTooManyMenu(final List<String> inputMenus) {
        return inputMenus.stream()
                .map(inputMenu -> inputMenu.split(DELIMITER))
                .mapToInt(splitMenu -> Integer.parseInt(splitMenu[COUNT_INDEX]))
                .sum() > LIMIT_MENU_COUNT;
    }
}
