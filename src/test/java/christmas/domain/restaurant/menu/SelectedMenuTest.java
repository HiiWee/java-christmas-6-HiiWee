package christmas.domain.restaurant.menu;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import christmas.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SelectedMenuTest {


    @DisplayName("메뉴를 선택할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드", "바비큐립", "해산물파스타"})
    void create_selectedMenu(String menuName) {
        // given & when & then
        assertThatNoException().isThrownBy(() -> SelectedMenu.createFrom(menuName, 1));
    }

    @DisplayName("메뉴 이름이 존재하지 않는 이름이라면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"없는", "메뉴", "이름"})
    void selectMenu_exception_notFoundMenuName(String invalidMenuName) {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedMenu.createFrom(invalidMenuName, 1))
                .withMessageContaining(DomainExceptionMessage.INVALID_ORDER.message());
    }

    @DisplayName("주문하는 메뉴 수량이 1개 미만이라면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -3})
    void selectMenu_exception_outOfBoundCount(int invalidCount) {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedMenu.createFrom("시저샐러드", invalidCount))
                .withMessageContaining(DomainExceptionMessage.INVALID_ORDER.message());
    }
}