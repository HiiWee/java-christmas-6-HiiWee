package christmas.domain.event.discount;

import christmas.domain.event.EventType;
import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public class SpecialDiscountEvent implements DiscountEvent {

    private static final int DISCOUNT_INCREMENT = 1000;

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.SPECIAL_EVENT)) {
            history.addParticipatedEvent(EventType.SPECIAL_EVENT, DISCOUNT_INCREMENT);
        }
    }
}
