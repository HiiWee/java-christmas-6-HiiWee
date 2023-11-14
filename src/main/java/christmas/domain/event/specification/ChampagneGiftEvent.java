package christmas.domain.event.list;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.menu.Menu;
import christmas.domain.restaurant.reservation.Reservation;

public class ChampagneGiftEvent implements Event {

    private static final int MINIMUM_TOTAL_PRICE = 120_000;

    @Override
    public boolean canJoinEvent(final Reservation reservation) {
        return reservation.hasHigherOrSamePrice(MINIMUM_TOTAL_PRICE);
    }

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (canJoinEvent(reservation)) {
            history.addFreeGift(Menu.CHAMPAGNE);
        }
    }
}
