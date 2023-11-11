package christmas.domain;

import christmas.domain.date.SelectedDate;
import christmas.domain.menu.SelectedMenus;
import christmas.domain.reservation.Reservation;
import christmas.dto.ReservedResults;
import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.List;
import java.util.Optional;

public class WootecoRestaurantManager {

    private final BookingRepository bookingRepository;

    public WootecoRestaurantManager(final BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void addSelectedDate(final String inputDate) {
        SelectedDate selectedDate = SelectedDate.createFrom(inputDate);
        bookingRepository.saveSelectedDate(selectedDate);
    }

    public void addSelectedMenus(final List<String> inputMenus) {
        SelectedMenus selectedMenus = SelectedMenus.createFrom(inputMenus);
        bookingRepository.saveSelectedMenus(selectedMenus);
    }

    public ReservedResults createReservationResults() {
        Reservation reservation = findReservationObject();
        return ReservedResults.createFrom(reservation);
    }

    public void updateReservation() {
        Optional<Reservation> optionalReservation = bookingRepository.findReservation();
        if (optionalReservation.isEmpty()) {
            Reservation reservation = new Reservation(findSelectedMenusObject(), findSelectedDateObject());
            bookingRepository.saveReservation(reservation);
        }
    }

    public Reservation findReservationObject() {
        return bookingRepository.findReservation()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_RESERVATION::create);
    }

    private SelectedDate findSelectedDateObject() {
        return bookingRepository.findSelectedDate()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_SELECTED_DATE::create);
    }

    private SelectedMenus findSelectedMenusObject() {
        return bookingRepository.findSelectedMenus()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_MENU_TYPE::create);
    }
}
