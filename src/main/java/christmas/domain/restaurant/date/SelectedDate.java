package christmas.domain.restaurant.date;

import christmas.validator.domain.exception.DomainExceptionMessage;

public record SelectedDate(int date) {

    public static SelectedDate createFrom(final int inputDate) {
        validate(inputDate);
        return new SelectedDate(inputDate);
    }

    private static void validate(final int inputDate) {
        validateRange(inputDate);
    }

    private static void validateRange(final int inputDate) {
        if (DateCondition.isInvalidDate(inputDate)) {
            throw DomainExceptionMessage.INVALID_INPUT_DATE.create();
        }
    }
}
