package christmas.domain.restaurant.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import christmas.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SelectedMenuTest {

    @DisplayName("메뉴를 선택할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-1", "바비큐립-1", "해산물파스타-1"})
    void create_selectedMenu(String menuName) {
        // given & when & then
        assertThatNoException().isThrownBy(() -> SelectedMenu.createFrom(menuName));
    }

    @DisplayName("메뉴 이름이 존재하지 않는 이름이라면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"없는-1", "메뉴-1", "이름-1"})
    void selectMenu_exception_notFoundMenuName(String invalidMenuName) {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedMenu.createFrom(invalidMenuName))
                .withMessageContaining(DomainExceptionMessage.INVALID_ORDER.message());
    }

    @DisplayName("주문하는 메뉴 수량이 1개 미만이라면 예외가 발생합니다.")
    @Test
    void selectMenu_exception_outOfBoundCount() {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedMenu.createFrom("시저샐러드-0"))
                .withMessageContaining(DomainExceptionMessage.INVALID_ORDER.message());
    }

    @DisplayName("동일한 메뉴 타입이라면 참을 반환한다.")
    @Test
    void isSameMenuType() {
        // given
        SelectedMenu selectedMenu = SelectedMenu.createFrom("시저샐러드-10");

        // when
        boolean isSameMenuType = selectedMenu.isTypeOf(MenuType.APPETIZER);

        // then
        assertThat(isSameMenuType).isTrue();
    }

    @DisplayName("예약한 단일 메뉴에 대한 총 가격을 계산한다.")
    @Test
    void calculateSinglePrice() {
        // given
        SelectedMenu selectedMenu = SelectedMenu.createFrom("시저샐러드-10");

        // when
        int singlePrice = selectedMenu.calculateSinglePrice();

        // then
        assertThat(singlePrice).isEqualTo(Menu.CAESAR_SALAD.price() * 10);
    }
}