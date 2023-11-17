package christmas.domain.restaurant.date;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum DateType {

    CHRISTMAS(ChristmasPromotionDate::isInChristmas),
    WEEKDAY(ChristmasPromotionDate::isWeekday),
    WEEKEND(ChristmasPromotionDate::isWeekend),
    SPECIAL(ChristmasPromotionDate::isSpecial),
    NONE(ignoreDate -> false);

    private final Predicate<LocalDate> dateChecker;

    DateType(final Predicate<LocalDate> dateChecker) {
        this.dateChecker = dateChecker;
    }

    public static List<DateType> findDatTypes(final LocalDate date) {
        return Arrays.stream(values())
                .filter(dateType -> dateType.containsDate(date))
                .toList();
    }

    private boolean containsDate(final LocalDate date) {
        return dateChecker.test(date);
    }
}
