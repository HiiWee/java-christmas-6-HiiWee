package christmas.domain.event.eventhistory;

import christmas.domain.restaurant.menu.Menu;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record EventGifts(Map<Menu, Integer> giftCounts) {

    @Override
    public Map<Menu, Integer> giftCounts() {
        return Collections.unmodifiableMap(giftCounts);
    }

    public void add(final Menu menu) {
        giftCounts.put(menu, giftCounts.getOrDefault(menu, 0) + 1);
    }

    public Map<String, Integer> convertMenuToName() {
        return giftCounts.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getName(), Entry::getValue));
    }
}
