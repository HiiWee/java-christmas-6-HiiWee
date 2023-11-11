package christmas.dto;

public record PaymentAmountResult(int paymentAmount) {

    private static final String MESSAGE_FORMAT = "%,d원";

    public String createMessage() {
        return String.format(MESSAGE_FORMAT, paymentAmount);
    }
}
