package christmas.validator.domain.exception;

public enum DomainExceptionMessage {

    INVALID_INPUT_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    DomainExceptionMessage(final String message) {
        this.message = message;
    }

    public DomainIllegalArgumentException create() {
        return new DomainIllegalArgumentException(message);
    }

    public String message() {
        return message;
    }
}
