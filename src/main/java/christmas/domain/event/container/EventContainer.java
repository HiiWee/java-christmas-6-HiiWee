package christmas.domain.event.container;

import christmas.domain.event.history.EventJoinHistory;
import christmas.domain.event.specification.ChampagneGiftEvent;
import christmas.domain.event.specification.ChristmasDiscountEvent;
import christmas.domain.event.specification.Event;
import christmas.domain.event.specification.SpecialDiscountEvent;
import christmas.domain.event.specification.WeekdayDiscountEvent;
import christmas.domain.event.specification.WeekendDiscountEvent;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.Collections;
import java.util.List;

public record EventContainer(List<Event> events) {

    public static EventContainer getInstance() {
        return new EventContainer(
                List.of(
                        new ChristmasDiscountEvent(),
                        new WeekdayDiscountEvent(),
                        new WeekendDiscountEvent(),
                        new SpecialDiscountEvent(),
                        new ChampagneGiftEvent()
                ));
    }

    private boolean canJoinAnyEvent(final Reservation reservation) {
        int totalPrice = reservation.getTotalPrice();
        return EventCondition.canJoinEventPrice(totalPrice);
    }

    public void joinEvents(final Reservation reservation, final EventJoinHistory history) {
        if (canJoinAnyEvent(reservation)) {
            events.forEach(event -> event.participateEvent(history, reservation));
        }
    }

    @Override
    public List<Event> events() {
        return Collections.unmodifiableList(events);
    }
}
