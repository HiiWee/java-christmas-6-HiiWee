package christmas.domain.event;

import christmas.domain.event.eventhistory.EventJoinHistory;
import java.util.Optional;

public class EventJoinHistoryRepository {

    private EventJoinHistory history;

    public void saveEventHistory(final EventJoinHistory history) {
        this.history = history;
    }

    public Optional<EventJoinHistory> findEventHistory() {
        return Optional.ofNullable(history);
    }
}
