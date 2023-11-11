package christmas.domain.event.discount;

import christmas.domain.event.EventType;
import christmas.domain.event.history.EventParticipationHistory;
import christmas.domain.menu.MenuType;
import christmas.domain.reservation.Reservation;

public class WeekendDiscountEvent implements DiscountEvent {

    private static final MenuType TARGET_MENU_TYPE = MenuType.MAIN;
    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.WEEKEND_EVENT)) {
            int mainMenuCount = reservation.countMenuTypeFrom(TARGET_MENU_TYPE);
            history.participateEvent(EventType.WEEKEND_EVENT, DISCOUNT_INCREMENT * mainMenuCount);
        }
    }
}
