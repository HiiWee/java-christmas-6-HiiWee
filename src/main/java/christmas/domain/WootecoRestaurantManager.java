package christmas.domain;

import christmas.domain.date.SelectedDate;
import christmas.domain.menu.SelectedMenus;
import christmas.domain.reservation.Reservation;
import christmas.dto.ReservedResults;
import christmas.validator.domain.exception.DomainExceptionMessage;
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

    public ReservedResults createReservationResults() {
        Reservation reservation = findReservationObject();
        return ReservedResults.createFrom(reservation);
    }

    private Reservation findReservationObject() {
        SelectedDate selectedDate = bookingRepository.findSelectedDate()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_SELECTED_DATE::create);
        SelectedMenus selectedMenus = bookingRepository.findSelectedMenus()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_SELECTED_MENUS::create);
        return new Reservation(selectedMenus, selectedDate);
    }
}
