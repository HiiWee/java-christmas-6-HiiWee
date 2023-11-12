package christmas.dto;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;

public record PaymentAmountResult(int paymentAmount) {

    private static final String MESSAGE_FORMAT = "%,d원";

    public static PaymentAmountResult createFrom(final EventJoinHistory history, final Reservation reservation) {
        int totalPrice = reservation.getTotalPrice();
        int totalBenefit = history.calculateTotalBenefit();
        return new PaymentAmountResult(totalPrice - totalBenefit);
    }

    public String createMessage() {
        return String.format(MESSAGE_FORMAT, paymentAmount);
    }
}
