package christmas.domain.event.specification;

import christmas.domain.event.EventType;
import christmas.domain.event.history.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public class ChristmasDiscountEvent implements Event {

    private static final int START_DATE = 1;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_INCREMENT = 100;

    @Override
    public boolean canJoinEvent(final Reservation reservation) {
        return reservation.containsEventType(EventType.CHRISTMAS_EVENT);
    }

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (canJoinEvent(reservation)) {
            int date = reservation.getReservedDate();
            history.addParticipatedEvent(
                    EventType.CHRISTMAS_EVENT,
                    DEFAULT_DISCOUNT_AMOUNT + (date - START_DATE) * DISCOUNT_INCREMENT
            );
        }
    }
}
