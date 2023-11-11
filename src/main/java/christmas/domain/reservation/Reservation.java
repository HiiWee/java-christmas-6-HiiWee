package christmas.domain.reservation;

import christmas.domain.date.SelectedDate;
import christmas.domain.event.EventType;
import christmas.domain.menu.MenuType;
import christmas.domain.menu.SelectedMenu;
import christmas.domain.menu.SelectedMenus;
import java.util.List;

public class Reservation {

    private final SelectedMenus selectedMenus;
    private final SelectedDate selectedDate;

    public Reservation(final SelectedMenus selectedMenus, final SelectedDate selectedDate) {
        this.selectedMenus = selectedMenus;
        this.selectedDate = selectedDate;
    }

    public boolean containsEventType(final EventType eventType) {
        List<EventType> reservationEventTypes = findEventTypes();
        return reservationEventTypes.contains(eventType);
    }

    private List<EventType> findEventTypes() {
        return EventType.findEventTypesFrom(selectedDate.dateTypes());
    }

    public int countMenuTypeFrom(final MenuType targetMenuType) {
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
}

