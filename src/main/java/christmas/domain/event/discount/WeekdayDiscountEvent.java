package christmas.domain.event.discount;

import christmas.domain.event.EventType;
import christmas.domain.event.eventhistory.EventParticipationHistory;
import christmas.domain.restaurant.menu.MenuType;
import christmas.domain.restaurant.reservation.Reservation;

public class WeekdayDiscountEvent implements DiscountEvent {

    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.WEEKDAY_EVENT)) {
            int dessertMenuCount = reservation.countMenuTypeFrom(MenuType.DESSERT);
            history.participateEvent(EventType.WEEKDAY_EVENT, DISCOUNT_INCREMENT * dessertMenuCount);
        }
    }
}
