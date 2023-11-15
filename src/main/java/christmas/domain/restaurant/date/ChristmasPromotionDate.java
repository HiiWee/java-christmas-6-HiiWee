package christmas.domain.restaurant.date;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class ChristmasPromotionDate {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    private static final LocalDate CHRISTMAS_DATE = LocalDate.of(YEAR, MONTH, 26);
    private static final List<Integer> SPECIAL_DATE = List.of(3, 10, 17, 24, 25, 31);


    public static boolean isInChristmas(final int date) {
        LocalDate localDate = createPromotionDate(date);
        return localDate.isBefore(CHRISTMAS_DATE);
    }

    public static boolean isWeekday(final int date) {
        return !isWeekend(date);
    }

    public static boolean isWeekend(final int date) {
        LocalDate localDate = createPromotionDate(date);
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public static boolean isSpecial(final int date) {
        return SPECIAL_DATE.contains(date);
    }

    public static boolean getNone(final int date) {
        return false;
    }

    private static LocalDate createPromotionDate(final int date) {
        return LocalDate.of(YEAR, MONTH, date);
    }
}
