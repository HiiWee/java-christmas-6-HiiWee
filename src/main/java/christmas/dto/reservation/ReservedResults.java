package christmas.dto.reservation;

import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;
import java.util.stream.Collectors;

public record ReservedResults(List<ReservedMenu> menus, int date, int totalPrice) {

    private static final String DELIMITER = "\n";
    private static final String PRICE_FORMAT = "%,d원";

    public static ReservedResults createFrom(final Reservation reservation) {
        List<ReservedMenu> menus = reservation.getSelectedMenus()
                .stream()
                .map(ReservedMenu::createFrom)
                .toList();
        int totalPrice = reservation.getTotalPrice();
        int date = reservation.getReservedDate();
        return new ReservedResults(menus, date, totalPrice);
    }

    public String createMenuNameMessage() {
        return menus.stream()
                .map(ReservedMenu::createMenuMessage)
                .collect(Collectors.joining(DELIMITER));
    }

    public String createTotalPriceMessage() {
        return String.format(PRICE_FORMAT, totalPrice);
    }
}
