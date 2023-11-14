package christmas.domain.event.eventhistory;

import christmas.domain.restaurant.menu.Menu;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record EventGifts(Map<Menu, Integer> giftCounts) {

    private static final int DEFAULT_VALUE = 0;
    private static final int INCREASE_AMOUNT = 1;

    public void add(final Menu menu) {
        giftCounts.put(menu, giftCounts.getOrDefault(menu, DEFAULT_VALUE) + INCREASE_AMOUNT);
    }

    public Map<String, Integer> convertMenuToName() {
        return giftCounts.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getName(), Entry::getValue));
    }

    @Override
    public Map<Menu, Integer> giftCounts() {
        return Collections.unmodifiableMap(giftCounts);
    }
}
