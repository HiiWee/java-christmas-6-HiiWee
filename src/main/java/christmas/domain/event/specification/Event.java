package christmas.domain.event.specification;

import christmas.domain.event.history.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public interface Event {

    boolean canJoinEvent(final Reservation reservation);

    void participateEvent(final EventJoinHistory history, final Reservation reservation);
}
