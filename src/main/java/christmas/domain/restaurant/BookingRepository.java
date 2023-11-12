package christmas.domain.restaurant;

import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
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

    public void saveReservation(final Reservation reservation) {
        stores.put(BookingType.RESERVATION, reservation);
    }

    public Optional<SelectedDate> findSelectedDate() {
        return Optional.ofNullable((SelectedDate) stores.get(BookingType.SELECTED_DATE));
    }

    public Optional<SelectedMenus> findSelectedMenus() {
        return Optional.ofNullable((SelectedMenus) stores.get(BookingType.SELECTED_MENUS));
    }

    public Optional<Reservation> findReservation() {
        return Optional.ofNullable((Reservation) stores.get(BookingType.RESERVATION));
    }
}
