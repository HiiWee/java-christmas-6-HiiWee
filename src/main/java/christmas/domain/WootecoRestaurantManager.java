package christmas.domain;

import christmas.domain.date.SelectedDate;
import christmas.domain.menu.SelectedMenus;
import java.util.List;

public class WootecoRestaurantManager {

    private final BookingRepository bookingRepository;

    public WootecoRestaurantManager(final BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void addSelectedDate(final String inputDate) {
        SelectedDate selectedDate = SelectedDate.createFrom(inputDate);
        bookingRepository.saveSelectedDate(selectedDate);
    }

    public void addSelectedMenu(final List<String> inputMenus) {
        SelectedMenus selectedMenus = SelectedMenus.createFrom(inputMenus);
        bookingRepository.saveSelectedMenus(selectedMenus);
    }
}
