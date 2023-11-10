package christmas.domain.event;

import java.util.Collections;
import java.util.Map;

public record EventParticipationHistory(Map<EventType, Integer> participatedEvents) {

    private static final int DEFAULT_VALUE = 0;

    @Override
    public Map<EventType, Integer> participatedEvents() {
        return Collections.unmodifiableMap(participatedEvents);
    }

    public void participateEvent(final EventType eventType, final int benefitAmount) {
        participatedEvents.put(eventType, participatedEvents.getOrDefault(eventType, DEFAULT_VALUE) + benefitAmount);
    }
}
