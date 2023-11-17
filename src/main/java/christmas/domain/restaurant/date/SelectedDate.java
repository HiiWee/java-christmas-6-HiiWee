package christmas.domain.restaurant.date;

import christmas.validator.domain.exception.DomainExceptionMessage;
import java.time.DateTimeException;
import java.time.LocalDate;

public record SelectedDate(LocalDate date) {

    public static SelectedDate createFrom(final int inputDate) {
        try {
            LocalDate date = ChristmasPromotionDate.createPromotionDate(inputDate);
            return new SelectedDate(date);
        } catch (DateTimeException e) {
            throw DomainExceptionMessage.INVALID_INPUT_DATE.create();
        }
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }
}
