package christmas.domain.restaurant.date;

import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.List;

public record SelectedDate(int date, List<DateType> dateTypes) {

    public static SelectedDate createFrom(final String inputDate) {
        validate(inputDate);
        int date = Integer.parseInt(inputDate);
        List<DateType> dateTypes = DateType.findWeekTypes(date);
        return new SelectedDate(date, dateTypes);
    }

    private static void validate(final String inputDate) {
        validateNumber(inputDate);
        validateRange(Integer.parseInt(inputDate));
    }

    private static void validateNumber(final String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException e) {
            throw DomainExceptionMessage.INVALID_INPUT_DATE.create();
        }
    }

    private static void validateRange(final int inputDate) {
        if (DateCondition.isInvalidDate(inputDate)) {
            throw DomainExceptionMessage.INVALID_INPUT_DATE.create();
        }
    }
}

