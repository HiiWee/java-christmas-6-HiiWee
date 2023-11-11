package christmas.domain;

import christmas.domain.date.SelectedDate;
import christmas.domain.menu.SelectedMenus;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class BookingRepository {

    private final Map<BookingType, Object> stores = new EnumMap<>(BookingType.class);

    public void saveSelectedDate(final SelectedDate selectedDate) {
        stores.put(BookingType.SELECTED_DATE, selectedDate);
    }

    public void saveSelectedMenus(final SelectedMenus selectedMenus) {
        stores.put(BookingType.SELECTED_MENUS, selectedMenus);
    }

    public Optional<SelectedDate> findSelectedDate() {
        return Optional.ofNullable((SelectedDate) stores.get(BookingType.SELECTED_DATE));
    }

    public Optional<SelectedMenus> findSelectedMenus() {
        return Optional.ofNullable((SelectedMenus) stores.get(BookingType.SELECTED_MENUS));
    }
}
