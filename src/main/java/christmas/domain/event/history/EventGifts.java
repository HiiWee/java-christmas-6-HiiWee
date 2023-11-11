package christmas.domain.event.history;

import christmas.domain.menu.Menu;
import java.util.List;

public record EventGifts(List<Menu> menus) {

    public void add(final Menu menu) {
        menus.add(menu);
    }
}
