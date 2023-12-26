package christmas.dto.reservation;

import christmas.domain.restaurant.menu.SelectedMenu;

public record ReservedMenu(String name, int count) {

    private static final String MENU_FORMAT = "%s %d개";

    public static ReservedMenu createFrom(final SelectedMenu selectedMenu) {
        return new ReservedMenu(selectedMenu.getName(), selectedMenu.count());
    }

    public String createMenuMessage() {
        return String.format(MENU_FORMAT, name, count);
    }
}
