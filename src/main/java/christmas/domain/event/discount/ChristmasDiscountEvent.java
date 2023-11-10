package christmas.domain.event.discount;

import christmas.domain.date.SelectedDate;
import christmas.domain.event.EventParticipationHistory;
import christmas.domain.event.EventType;
import christmas.domain.reservation.Reservation;

public class ChristmasDiscountEvent implements DiscountEvent {

    // TODO 크리스마스 조건 분리
    private static final EventType CHRISTMAS_EVENT = EventType.CHRISTMAS_EVENT;
    private static final int START_DATE = 1;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_INCREMENT = 100;

    @Override
    public void participateEvent(final EventParticipationHistory history, final Reservation reservation) {
        if (reservation.containsEventType(CHRISTMAS_EVENT)) {
            SelectedDate selectedDate = reservation.selectedDate();
            history.participateEvent(
                    EventType.CHRISTMAS_EVENT,
                    DEFAULT_DISCOUNT_AMOUNT + (selectedDate.date() - START_DATE) * DISCOUNT_INCREMENT
            );
        }
    }
}
