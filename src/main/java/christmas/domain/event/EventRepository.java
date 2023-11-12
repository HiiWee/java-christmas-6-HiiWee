package christmas.domain.event;

import christmas.domain.event.eventhistory.EventParticipationHistory;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class EventRepository {

    private final Map<EventStoreType, Object> stores = new EnumMap<>(EventStoreType.class);

    public void saveEventHistory(final EventParticipationHistory history) {
        stores.put(EventStoreType.HISTORY, history);
    }

    public Optional<EventParticipationHistory> findEventHistory() {
        return Optional.ofNullable((EventParticipationHistory) stores.get(EventStoreType.HISTORY));
    }
}
