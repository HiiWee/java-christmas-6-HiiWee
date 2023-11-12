package christmas.domain.restaurant.date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateConditionTest {

    @DisplayName("1~31 사이의 날짜가 아니라면 참을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32, -1, 33})
    void isInvalidDate(int invalidDate) {
        // given & when
        boolean result = DateCondition.isInvalidDate(invalidDate);

        // then
        assertThat(result).isTrue();
    }

}