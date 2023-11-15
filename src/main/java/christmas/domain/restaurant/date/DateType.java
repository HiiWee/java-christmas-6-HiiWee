package christmas.domain.restaurant.date;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum DateType {

    CHRISTMAS(ChristmasPromotionDate::isInChristmas),
    WEEKDAY(ChristmasPromotionDate::isWeekday),
    WEEKEND(ChristmasPromotionDate::isWeekend),
    SPECIAL(ChristmasPromotionDate::isSpecial),
    NONE(ChristmasPromotionDate::getNone);

    private final Predicate<Integer> dateChecker;

    DateType(final Predicate<Integer> dateChecker) {
        this.dateChecker = dateChecker;
    }

    public static List<DateType> findDatTypes(final int date) {
        return Arrays.stream(values())
                .filter(dateType -> dateType.containsDate(date))
                .toList();
    }

    private boolean containsDate(final int date) {
        return dateChecker.test(date);
    }
}
