package christmas.validator.domain.exception;

public enum DomainExceptionMessage {

    INVALID_INPUT_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_FOUND_MENU_TYPE("[ERROR] 메뉴 타입을 찾을 수 없습니다."),
    NOT_FOUND_SELECTED_DATE("[ERROR] 선택한 날짜를 찾을 수 없습니다."),
    NOT_FOUND_SELECTED_MENUS("[ERROR] 선택한 메뉴들을 찾을 수 없습니다."),
    NOT_FOUND_RESERVATION("[ERROR] 선택한 예약을 찾을 수 없습니다."),
    NOT_FOUND_EVENT_HISTORY("[ERROR] 이벤트 참여 내역을 찾을 수 없습니다.");

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
