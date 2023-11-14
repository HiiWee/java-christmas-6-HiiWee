package christmas.domain.event.history;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        assertThat(totalBenefit).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }


    @DisplayName("할인 이벤트 가격과 전체 이벤트 가격을 계산할 수 있다.")
    @Test
    void calculate_benefit_discountBenefitOnly_or_totalBenefit() {
        // given
        EventJoinHistory history = EventJoinHistory.getInstance();
        history.addParticipatedEvent(EventType.CHRISTMAS_EVENT, 1000);
        history.addFreeGift(Menu.BARBECUE_RIBS);

        // when
        int discountBenefit = history.calculateDiscountBenefit();
        int totalBenefit = history.calculateTotalBenefit();

        // then
        assertAll(
                () -> assertThat(discountBenefit).isEqualTo(1000),
                () -> assertThat(totalBenefit).isEqualTo(55000)
        );
    }
}
