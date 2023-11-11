package christmas.domain.event.history;

import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.List;

public record EventGifts(List<Menu> menus) {

    @Override
    public List<Menu> menus() {
        return Collections.unmodifiableList(menus);
    }

    public void add(final Menu menu) {
        menus.add(menu);
    }
}
