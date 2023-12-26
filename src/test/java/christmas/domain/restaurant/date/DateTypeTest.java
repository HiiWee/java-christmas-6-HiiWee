package christmas.domain.restaurant.date;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTypeTest {

    @DisplayName("날짜에 대한 날짜 타입을 찾을 수 있다.")
    @Test
    void findDateTypes() {
        // given
        int date = 25;

        // when
        List<DateType> dateTypes = DateType.findDatTypes(date);

        // then
        assertThat(dateTypes).contains(DateType.CHRISTMAS, DateType.WEEKDAY, DateType.SPECIAL);
    }

}
