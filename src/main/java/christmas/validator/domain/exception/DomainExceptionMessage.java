package christmas.validator.domain.exception;

public enum DomainExceptionMessage {

    INVALID_INPUT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    TOO_MANY_RESERVATION_MENU("20개 이하의 메뉴만 주문할 수 있습니다."),
    NOT_FOUND_MENU_TYPE("메뉴 타입을 찾을 수 없습니다."),
    NOT_FOUND_SELECTED_DATE("선택한 날짜를 찾을 수 없습니다."),
    NOT_FOUND_SELECTED_MENUS("선택한 메뉴들을 찾을 수 없습니다."),
    NOT_FOUND_RESERVATION("선택한 예약을 찾을 수 없습니다."),
    NOT_FOUND_EVENT_HISTORY("이벤트 참여 내역을 찾을 수 없습니다."),
    CAN_NOT_RESERVE_ONLY_BEVERAGE("음료만 예약할 수 없습니다.");

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
