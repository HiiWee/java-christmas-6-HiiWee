package christmas.domain.event.discount;

import christmas.domain.event.EventType;
import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.menu.MenuType;
import christmas.domain.restaurant.reservation.Reservation;

public class WeekendDiscountEvent implements DiscountEvent {

    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.WEEKEND_EVENT)) {
            int mainMenuCount = reservation.countSameMenuType(MenuType.MAIN);
            history.participateEvent(EventType.WEEKEND_EVENT, DISCOUNT_INCREMENT * mainMenuCount);
        }
    }
}
