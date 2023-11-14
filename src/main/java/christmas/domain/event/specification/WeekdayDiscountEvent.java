package christmas.domain.event.specification;

import christmas.domain.event.EventType;
import christmas.domain.event.history.EventJoinHistory;
import christmas.domain.restaurant.menu.MenuType;
import christmas.domain.restaurant.reservation.Reservation;

public class WeekdayDiscountEvent implements Event {

    private static final int DISCOUNT_INCREMENT = 2023;

    @Override
    public boolean canJoinEvent(final Reservation reservation) {
        return reservation.containsEventType(EventType.WEEKDAY_EVENT);
    }

    @Override
    public void participateEvent(final EventJoinHistory history, final Reservation reservation) {
        if (canJoinEvent(reservation)) {
            int dessertMenuCount = reservation.countSameMenuType(MenuType.DESSERT);
            history.addParticipatedEvent(EventType.WEEKDAY_EVENT, DISCOUNT_INCREMENT * dessertMenuCount);
        }
    }
}
