package christmas.domain.event;

import christmas.domain.event.history.EventParticipationHistory;
import christmas.domain.menu.MenuType;
import christmas.domain.reservation.Reservation;
import java.util.List;

public interface Event {

    static boolean canJoinAnyEvent(final Reservation reservation) {
        List<MenuType> menuTypes = reservation.getMenuTypes();
        int totalPrice = reservation.getTotalPrice();

        // TODO 이벤트 참여 조건 분리
        return MenuType.isNotOnlyBeverage(menuTypes) && totalPrice >= 10_000;
    }

    void participateEvent(final EventParticipationHistory history, final Reservation reservation);
}
