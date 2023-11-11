package christmas.domain.event.history;

import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.Map;

public record EventGifts(Map<Menu, Integer> giftCounts) {

    @Override
    public Map<Menu, Integer> giftCounts() {
        return Collections.unmodifiableMap(giftCounts);
    }

    public void add(final Menu menu) {
        giftCounts.put(menu, giftCounts.getOrDefault(menu, 0) + 1);
    }
}
