package christmas.domain.restaurant.date;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SelectedDateTest {

    @DisplayName("1~31 사이의 날짜가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "100", "-1"})
    void createSelectedDate_exception_invalidDate(String invalidDate) {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> SelectedDate.createFrom(invalidDate))
                .withMessageContaining(DomainExceptionMessage.INVALID_INPUT_DATE.message());
    }
}