package christmas.domain.event;

import christmas.domain.menu.MenuType;
import christmas.domain.reservation.Reservation;

public class WeekdayEvent implements Event {

    private static final EventType WEEKDAY_EVENT = EventType.WEEKDAY_EVENT;
    private static final MenuType TARGET_MENU_TYPE = MenuType.DESSERT;
    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public int calculateDiscountPrice(final Reservation reservation) {
        if (reservation.containsEventType(WEEKDAY_EVENT)) {
            int dessertMenuCount = reservation.countMenuTypeFrom(TARGET_MENU_TYPE);
            return DISCOUNT_INCREMENT * dessertMenuCount;
        }
        // TODO 상수 분리
        return 0;
    }
}
