package christmas.domain.restaurant.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuConditionTest {

    @DisplayName("1개보다 작은 메뉴 갯수가 입력되면 참을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2})
    void isInvalidMenuCount(int invalidMenuCount) {
        // given & when
        boolean result = MenuCondition.isInvalidMenuCount(invalidMenuCount);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("20개 이상의 메뉴가 예약되면 참을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {21, 22, 23, 24})
    void hasTooManyMenu(int totalMenuCount) {
        // given & when
        boolean hasTooManyMenu = MenuCondition.hasTooManyMenu(totalMenuCount);

        // then
        assertThat(hasTooManyMenu).isTrue();
    }
}
