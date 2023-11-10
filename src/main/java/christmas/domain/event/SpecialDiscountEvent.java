package christmas.domain.event;

import christmas.domain.reservation.Reservation;

public class SpecialDiscountEvent implements DiscountEvent {

    private static final int DISCOUNT_INCREMENT = 1000;

    @Override
    public int calculateDiscountPrice(final Reservation reservation) {
        if (reservation.containsEventType(EventType.SPECIAL_EVENT)) {
            return DISCOUNT_INCREMENT;
        }
        return 0;
    }
}
