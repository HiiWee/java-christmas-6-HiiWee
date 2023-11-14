package christmas.domain.event.list;

import christmas.domain.event.EventType;
import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public class SpecialDiscountEvent implements Event {

    private static final int DISCOUNT_INCREMENT = 1000;

    @Override
    public boolean canJoinEvent(final Reservation reservation) {
        return reservation.containsEventType(EventType.SPECIAL_EVENT);
    }

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (canJoinEvent(reservation)) {
            history.addParticipatedEvent(EventType.SPECIAL_EVENT, DISCOUNT_INCREMENT);
        }
    }
}
