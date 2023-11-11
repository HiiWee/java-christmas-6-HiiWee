package christmas.domain.event.gift;

import christmas.domain.event.Event;
import christmas.domain.reservation.Reservation;

public interface GiftEvent extends Event {

    int MINIMUM_TOTAL_PRICE = 120_000;

    default boolean canGetGift(final Reservation reservation) {
        return reservation.hasHigherOrSamePrice(MINIMUM_TOTAL_PRICE);
    }
}
