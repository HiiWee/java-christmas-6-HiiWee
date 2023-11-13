package christmas.domain.event.discount;

import christmas.domain.event.EventType;
import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public class ChristmasDiscountEvent implements DiscountEvent {

    private static final int START_DATE = 1;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_INCREMENT = 100;

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.CHRISTMAS_EVENT)) {
            int date = reservation.getReservedDate();
            history.addParticipatedEvent(
                    EventType.CHRISTMAS_EVENT,
                    DEFAULT_DISCOUNT_AMOUNT + (date - START_DATE) * DISCOUNT_INCREMENT
            );
        }
    }
}
