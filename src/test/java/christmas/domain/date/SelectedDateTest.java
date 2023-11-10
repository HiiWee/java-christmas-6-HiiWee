package christmas.domain.date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("날짜를 지정하면 해당되는 주간 타입도 지정됩니다.")
    @Test
    void afterCreateSelectedDate_withWeekType() {
        // given
        String date1 = "1"; // 크리스마스, 주말
        String date2 = "25"; // 평일, 특별

        // when
        SelectedDate selectedDate1 = SelectedDate.createFrom(date1);
        SelectedDate selectedDate2 = SelectedDate.createFrom(date2);

        // then
        assertAll(
                () -> assertThat(selectedDate1.dateTypes()).contains(DateType.CHRISTMAS, DateType.WEEKEND),
                () -> assertThat(selectedDate2.dateTypes()).contains(DateType.WEEKDAY, DateType.SPECIAL)
        );
    }

}