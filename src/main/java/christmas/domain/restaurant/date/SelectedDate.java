package christmas.domain.restaurant.date;

import christmas.validator.domain.exception.DomainExceptionMessage;

public record SelectedDate(int date) {

    public static SelectedDate createFrom(final String inputDate) {
        validate(inputDate);
        int date = Integer.parseInt(inputDate);
        return new SelectedDate(date);
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

