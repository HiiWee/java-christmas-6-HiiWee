package christmas.view;

import java.util.List;

public class InputView implements InputViewable {

    private static final String INPUT_VISIT_DAY_MESSAGE = """
            안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)""";
    private static final String INPUT_MENUS_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    @Override
    public int inputVisitDate() {
        System.out.println(INPUT_VISIT_DAY_MESSAGE);
        return InputReader.readDate();
    }

    @Override
    public List<String> inputMenus() {
        System.out.println(INPUT_MENUS_MESSAGE);
        return InputReader.readMenus();
    }
}
