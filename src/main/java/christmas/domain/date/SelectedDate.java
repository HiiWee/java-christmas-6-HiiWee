package christmas.domain.date;

import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.List;

public record SelectedDate(int date, List<DateType> dateTypes) {

    public static SelectedDate createFrom(final int date) {
        List<DateType> dateTypes = DateType.findWeekTypes(date);
        validate(dateTypes);
        return new SelectedDate(date, dateTypes);
    }

    private static void validate(final List<DateType> dateTypes) {
        if (dateTypes.isEmpty()) {
            throw DomainExceptionMessage.INVALID_INPUT_DATE.create();
        }
    }
}

