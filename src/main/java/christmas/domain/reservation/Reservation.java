package christmas.domain.reservation;

import christmas.domain.date.SelectedDate;
import christmas.domain.menu.SelectedMenus;
import java.util.List;

public record Reservation(SelectedMenus selectedMenus, SelectedDate selectedDate) {

    public static Reservation createFrom(final List<String> inputMenus, final int date) {
        return new Reservation(
                SelectedMenus.createFrom(inputMenus),
                SelectedDate.createFrom(date)
        );
    }
}
