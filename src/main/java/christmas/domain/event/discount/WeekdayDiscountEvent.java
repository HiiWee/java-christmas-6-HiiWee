package christmas.domain.event.discount;

import christmas.domain.event.EventParticipationHistory;
import christmas.domain.event.EventType;
import christmas.domain.menu.MenuType;
import christmas.domain.reservation.Reservation;

public class WeekdayDiscountEvent implements DiscountEvent {

    private static final MenuType TARGET_MENU_TYPE = MenuType.DESSERT;
    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.WEEKDAY_EVENT)) {
            int dessertMenuCount = reservation.countMenuTypeFrom(TARGET_MENU_TYPE);
            history.participateEvent(EventType.WEEKDAY_EVENT, DISCOUNT_INCREMENT * dessertMenuCount);
        }
    }
}
