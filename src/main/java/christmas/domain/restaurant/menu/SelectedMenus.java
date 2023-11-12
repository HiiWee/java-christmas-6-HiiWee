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
}
