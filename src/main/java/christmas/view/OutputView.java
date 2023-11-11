package christmas.view;

import christmas.dto.ReservedResults;

public class OutputView {

    private static final String EVENT_BENEFIT_INFORMATION_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String RESERVED_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_WITHOUT_BENEFIT_MESSAGE = "<할인 전 총주문 금액>";

    public void printReservationResults(final ReservedResults results) {
        System.out.println(EVENT_BENEFIT_INFORMATION_MESSAGE);
        printReservedMenu(results);
        printTotalPrice(results);
    }

    private void printReservedMenu(final ReservedResults results) {
        System.out.println(RESERVED_MENU_MESSAGE);
        System.out.println(results.createMenuNameMessage());
    }

    private void printTotalPrice(final ReservedResults results) {
        System.out.println(TOTAL_PRICE_WITHOUT_BENEFIT_MESSAGE);
        System.out.println(results.createTotalPriceMessage());
    }
}
