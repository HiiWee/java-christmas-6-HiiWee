package christmas.domain.event.discount;

import christmas.domain.event.EventParticipationHistory;
import christmas.domain.event.EventType;
import christmas.domain.reservation.Reservation;

public class SpecialDiscountEvent implements DiscountEvent {

    private static final int DISCOUNT_INCREMENT = 1000;

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.SPECIAL_EVENT)) {
            history.participateEvent(EventType.SPECIAL_EVENT, DISCOUNT_INCREMENT);
        }
    }
}
