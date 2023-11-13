package christmas.domain.event.eventhistory;

import christmas.domain.event.EventType;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public int extractDiscountBenefit() {
        return events.entrySet()
                .stream()
                .filter(entry -> EventType.isNotGivingEvent(entry.getKey()))
                .mapToInt(Entry::getValue)
                .sum();
    }

    public Map<String, Integer> convertBenefitPricesWithName() {
        return events.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getEventName(),
                        Entry::getValue
                ));
    }
}
