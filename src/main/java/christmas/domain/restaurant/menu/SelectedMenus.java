package christmas.domain.restaurant.menu;

import christmas.validator.domain.SelectedMenusValidator;
import java.util.Collections;
import java.util.List;

public record SelectedMenus(List<SelectedMenu> menus) {

    public static SelectedMenus createFrom(List<String> inputMenus) {
        SelectedMenusValidator.validateMenus(inputMenus);
        return new SelectedMenus(inputMenus.stream()
                .map(SelectedMenu::createFrom)
                .toList());
    }

    public List<MenuType> extractUniqueMenuTypes() {
        return menus.stream()
                .map(SelectedMenu::menu)
                .map(MenuType::findType)
                .distinct()
                .toList();
    }

    public int extractSameTypeCount(final MenuType compareType) {
        return menus.stream()
                .filter(selectedMenu -> selectedMenu.isTypeOf(compareType))
                .mapToInt(SelectedMenu::count)
                .sum();
    }

    public int calculateTotalPrice() {
        return menus.stream()
                .mapToInt(SelectedMenu::calculateSinglePrice)
                .sum();
    }

    @Override
    public List<SelectedMenu> menus() {
        return Collections.unmodifiableList(menus);
    }
}
