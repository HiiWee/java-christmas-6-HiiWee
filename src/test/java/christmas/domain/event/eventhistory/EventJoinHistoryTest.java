package christmas.domain.event.eventhistory;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.EventType;
import christmas.domain.restaurant.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventJoinHistoryTest {

    @DisplayName("이벤트에 참여할 수 있다.")
    @Test
    void participateEvent() {
        // given
        EventJoinHistory history = EventJoinHistory.getInstance();

        // when
        history.addParticipatedEvent(EventType.CHRISTMAS_EVENT, 5000);
        int totalBenefit = history.calculateTotalBenefit();

        // then
        assertThat(totalBenefit).isEqualTo(5000);
    }

    @DisplayName("메뉴를 증정할 수 있다.")
    @Test
    void addGift() {
        // given
        EventJoinHistory history = EventJoinHistory.getInstance();

        // when
        history.addFreeGift(Menu.CHAMPAGNE);
        int totalBenefit = history.calculateTotalBenefit();

        // then
        assertThat(totalBenefit).isEqualTo(Menu.CHAMPAGNE.price());
    }
}