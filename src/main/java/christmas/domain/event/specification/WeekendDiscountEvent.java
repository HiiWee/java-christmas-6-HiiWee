package christmas.domain.event.list;

import christmas.domain.event.EventType;
import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.menu.MenuType;
import christmas.domain.restaurant.reservation.Reservation;

public class WeekendDiscountEvent implements Event {

    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public boolean canJoinEvent(final Reservation reservation) {
        return reservation.containsEventType(EventType.WEEKEND_EVENT);
    }

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.WEEKEND_EVENT)) {
            int mainMenuCount = reservation.countSameMenuType(MenuType.MAIN);
            history.addParticipatedEvent(EventType.WEEKEND_EVENT, DISCOUNT_INCREMENT * mainMenuCount);
        }
    }
}
