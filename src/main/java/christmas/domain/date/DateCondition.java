package christmas.domain.date;

public enum DateCondition {

    MIN_DATE(1),
    MAX_DATE(31);

    private final int date;

    DateCondition(final int date) {
        this.date = date;
    }

    public static boolean isInvalidDate(final int date) {
        return MIN_DATE.date > date || MAX_DATE.date < date;
    }
}
