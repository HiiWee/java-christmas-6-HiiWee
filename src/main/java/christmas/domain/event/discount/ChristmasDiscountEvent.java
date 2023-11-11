package christmas.domain.event.discount;

import christmas.domain.event.EventType;
import christmas.domain.event.history.EventParticipationHistory;
import christmas.domain.reservation.Reservation;

public class ChristmasDiscountEvent implements DiscountEvent {

    // TODO 크리스마스 조건 분리
    private static final int START_DATE = 1;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_INCREMENT = 100;

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (reservation.containsEventType(EventType.CHRISTMAS_EVENT)) {
            System.out.println("pass");
            int date = reservation.getReservedDate();
            history.participateEvent(
                    EventType.CHRISTMAS_EVENT,
                    DEFAULT_DISCOUNT_AMOUNT + (date - START_DATE) * DISCOUNT_INCREMENT
            );
        }
    }
}
