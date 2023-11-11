package christmas.domain.event.history;

import christmas.domain.event.EventType;
import java.util.Collections;
import java.util.Map;

public record EventBenefitPrices(Map<EventType, Integer> events) {

    private static final int DEFAULT_VALUE = 0;

    @Override
    public Map<EventType, Integer> events() {
        return Collections.unmodifiableMap(events);
    }

    public void updateBenefit(final EventType eventType, final int benefitAmount) {
        events.put(eventType, events.getOrDefault(eventType, DEFAULT_VALUE) + benefitAmount);
    }

    public int extractTotalBenefit() {
        return events.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
