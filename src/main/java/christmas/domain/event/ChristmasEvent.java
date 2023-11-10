package christmas.domain.event;

import christmas.domain.date.SelectedDate;
import christmas.domain.reservation.Reservation;

public class ChristmasEvent implements Event {

    // TODO 크리스마스 조건 분리
    private static final EventType CHRISTMAS_EVENT = EventType.CHRISTMAS_EVENT;
    private static final int START_DATE = 1;
    public static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    public static final int DISCOUNT_INCREMENT = 100;

    @Override
    public int calculateDiscountPrice(final Reservation reservation) {
        if (reservation.containsEventType(CHRISTMAS_EVENT)) {
            SelectedDate selectedDate = reservation.selectedDate();
            return DEFAULT_DISCOUNT_AMOUNT + (selectedDate.date() - START_DATE) * DISCOUNT_INCREMENT;
        }
        return 0;
    }
}
