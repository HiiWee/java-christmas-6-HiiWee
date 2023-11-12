package christmas.domain.event.eventhistory;

import christmas.domain.event.EventType;
import christmas.domain.restaurant.menu.Menu;
import java.util.EnumMap;

public record EventParticipationHistory(EventBenefitPrices benefitPrices, EventGifts giftCounts) {

    private static final int ZERO_WON = 0;

    public static EventParticipationHistory getInstance() {
        return new EventParticipationHistory(
                new EventBenefitPrices(new EnumMap<>(EventType.class)),
                new EventGifts(new EnumMap<>(Menu.class))
        );
    }

    public void participateEvent(final EventType eventType, final int benefitAmount) {
        if (benefitAmount > ZERO_WON) {
            benefitPrices.updateBenefit(eventType, benefitAmount);
        }
    }

    public void addGift(final Menu giftMenu) {
        giftCounts.add(giftMenu);
    }

    public int calculateTotalBenefit() {
        return benefitPrices.extractTotalBenefit();
    }
}
