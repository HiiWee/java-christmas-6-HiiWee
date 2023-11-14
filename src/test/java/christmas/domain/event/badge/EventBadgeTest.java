package christmas.domain.event.badge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBadgeTest {

    @DisplayName("혜택 가격에 따른 배지를 찾을 수 있다.")
    @Test
    void findBadge() {
        // given
        List<Integer> benefits = List.of(40000, 15000, 6000, 4999);

        // when
        List<EventBadge> eventBadges = benefits.stream()
                .map(EventBadge::findBadge)
                .toList();

        // then
        assertThat(eventBadges).containsExactly(EventBadge.SANTA, EventBadge.TREE, EventBadge.STAR, EventBadge.NONE);
    }

}
