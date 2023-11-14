package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventConditionTest {

    @DisplayName("참여 가능한 가격이라면 참을 반환한다.")
    @Test
    void canParticipatePrice() {
        // given
        int price = 10000;

        // when
        boolean result = EventCondition.canJoinEventPrice(price);

        // then
        assertThat(result).isTrue();
    }
}