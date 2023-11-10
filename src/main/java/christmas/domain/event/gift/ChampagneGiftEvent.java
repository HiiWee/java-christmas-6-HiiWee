package christmas.domain.event.gift;

import christmas.domain.event.EventParticipationHistory;
import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;
import christmas.domain.reservation.Reservation;

public class ChampagneGiftEvent implements GiftEvent {

    private static final Menu GIFT_MENU = Menu.CHAMPAGNE;

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (canGetGift(reservation)) {
            history.participateEvent(EventType.GIVING_EVENT, GIFT_MENU.price());
        }
    }
}
