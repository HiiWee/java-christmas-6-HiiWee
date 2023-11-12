package christmas.domain.event.gift;

import christmas.domain.event.EventType;
import christmas.domain.event.eventhistory.EventParticipationHistory;
import christmas.domain.restaurant.menu.Menu;
import christmas.domain.restaurant.reservation.Reservation;

public class ChampagneGiftEvent implements GiftEvent {

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (canGetGift(reservation)) {
            history.participateEvent(EventType.GIVING_EVENT, Menu.CHAMPAGNE.price());
            history.addGift(Menu.CHAMPAGNE);
        }
    }
}
