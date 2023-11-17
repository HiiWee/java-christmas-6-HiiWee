package christmas.domain.restaurant.date;


import java.time.DayOfWeek;
import java.time.LocalDate;

public class ChristmasPromotionDate {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;
    private static final LocalDate CHRISTMAS_DATE = LocalDate.of(YEAR, MONTH, 25);

    private ChristmasPromotionDate() {
    }

    public static boolean isInChristmas(final LocalDate date) {
        return date.isBefore(CHRISTMAS_DATE) || date.getDayOfMonth() == CHRISTMAS_DAY;
    }

    public static boolean isWeekday(final LocalDate date) {
        return !isWeekend(date);
    }

    public static boolean isWeekend(final LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public static boolean isSpecial(final LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfMonth() == CHRISTMAS_DAY;
    }


    public static LocalDate createPromotionDate(final int date) {
        return LocalDate.of(YEAR, MONTH, date);
    }
}
