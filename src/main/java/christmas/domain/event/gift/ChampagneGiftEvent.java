package christmas.domain.event.gift;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.menu.Menu;
import christmas.domain.restaurant.reservation.Reservation;

public class ChampagneGiftEvent implements GiftEvent {

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (canGetGift(reservation)) {
            history.addFreeGift(Menu.CHAMPAGNE);
        }
    }
}
