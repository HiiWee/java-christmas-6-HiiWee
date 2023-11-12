package christmas.domain.event;

import christmas.domain.event.eventhistory.EventParticipationHistory;
import christmas.domain.restaurant.menu.MenuType;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.List;

public interface Event {

    static boolean canJoinAnyEvent(final Reservation reservation) {
        List<MenuType> menuTypes = reservation.getUniqueMenuTypes();
        int totalPrice = reservation.getTotalPrice();
        return MenuType.isNotOnlyBeverage(menuTypes) && EventCondition.canParticipatePrice(totalPrice);
    }

    void participateEvent(final EventParticipationHistory history, final Reservation reservation);
}
