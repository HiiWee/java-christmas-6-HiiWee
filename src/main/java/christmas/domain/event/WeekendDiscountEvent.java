package christmas.domain.event;

import christmas.domain.menu.MenuType;
import christmas.domain.reservation.Reservation;

public class WeekendDiscountEvent implements DiscountEvent {

    private static final EventType WEEKEND_EVENT = EventType.WEEKEND_EVENT;
    private static final MenuType TARGET_MENU_TYPE = MenuType.MAIN;
    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public int calculateDiscountPrice(final Reservation reservation) {
        if (reservation.containsEventType(WEEKEND_EVENT)) {
            int mainMenuCount = reservation.countMenuTypeFrom(TARGET_MENU_TYPE);
            return DISCOUNT_INCREMENT * mainMenuCount;
        }
        return 0;
    }
}
