package christmas.domain.restaurant.reservation;

import christmas.domain.event.EventType;
import christmas.domain.restaurant.date.DateType;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.MenuType;
import christmas.domain.restaurant.menu.SelectedMenu;
import christmas.domain.restaurant.menu.SelectedMenus;
import java.util.List;

public record Reservation(SelectedMenus selectedMenus, SelectedDate selectedDate) {

    public boolean containsEventType(final EventType eventType) {
        List<EventType> reservationEventTypes = findEventTypes();
        return reservationEventTypes.contains(eventType);
    }

    public int extractMenuTypeCount(final MenuType targetMenuType) {
        return selectedMenus.menus()
                .stream()
                .filter(selectedMenu -> selectedMenu.isSameMenuType(targetMenuType))
                .mapToInt(SelectedMenu::count)
                .sum();
    }

    public boolean hasHigherOrSamePrice(final int compareAmount) {
        return selectedMenus.calculateTotalPrice() >= compareAmount;
    }

    public List<SelectedMenu> getSelectedMenus() {
        return selectedMenus.menus();
    }

    public int getTotalPrice() {
        return selectedMenus.calculateTotalPrice();
    }

    public int getReservedDate() {
        return selectedDate.date();
    }

    public List<MenuType> getMenuTypes() {
        return selectedMenus.extractMenuTypes();
    }

    private List<EventType> findEventTypes() {
        List<DateType> datTypes = DateType.findDatTypes(selectedDate.date());
        return EventType.findEventTypesFrom(datTypes);
    }
}
