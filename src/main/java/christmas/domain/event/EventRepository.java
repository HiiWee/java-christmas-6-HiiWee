package christmas.domain.event;

import christmas.domain.event.discount.ChristmasDiscountEvent;
import christmas.domain.event.discount.SpecialDiscountEvent;
import christmas.domain.event.discount.WeekdayDiscountEvent;
import christmas.domain.event.discount.WeekendDiscountEvent;
import christmas.domain.event.gift.ChampagneGiftEvent;
import christmas.domain.event.history.EventParticipationHistory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EventRepository {

    private final List<Event> stores = new ArrayList<>();
    private EventParticipationHistory history;

    public EventRepository() {
        stores.addAll(
                List.of(new ChristmasDiscountEvent(),
                        new WeekdayDiscountEvent(),
                        new WeekendDiscountEvent(),
                        new SpecialDiscountEvent(),
                        new ChristmasDiscountEvent(),
                        new ChampagneGiftEvent())
        );
    }

    public List<Event> findAllEvents() {
        return Collections.unmodifiableList(stores);
    }

    public void saveEventHistory(final EventParticipationHistory history) {
        this.history = history;
    }

    public Optional<EventParticipationHistory> findEventHistory() {
        return Optional.ofNullable(history);
    }
}
