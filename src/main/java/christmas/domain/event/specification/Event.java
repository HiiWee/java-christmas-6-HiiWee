package christmas.domain.event.list;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public interface Event {

    boolean canJoinEvent(final Reservation reservation);

    void participateEvent(final EventJoinHistory history, final Reservation reservation);
}
