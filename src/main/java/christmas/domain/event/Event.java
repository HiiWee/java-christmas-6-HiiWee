package christmas.domain.event;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public interface Event {

    static boolean canJoinAnyEvent(final Reservation reservation) {
        int totalPrice = reservation.getTotalPrice();
        return EventCondition.canParticipatePrice(totalPrice);
    }

    void participateEvent(final EventJoinHistory history, final Reservation reservation);
}
