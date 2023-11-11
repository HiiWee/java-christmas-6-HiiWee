package christmas.domain.event.history;

import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;
import java.util.EnumMap;

public record EventParticipationHistory(EventBenefitPrices benefitPrices, EventGifts giftCounts) {

    public static EventParticipationHistory getInstance() {
        return new EventParticipationHistory(
                new EventBenefitPrices(new EnumMap<>(EventType.class)),
                new EventGifts(new EnumMap<>(Menu.class))
        );
    }

    public void participateEvent(final EventType eventType, final int benefitAmount) {
        benefitPrices.updateBenefit(eventType, benefitAmount);
    }

    public void addGift(final Menu giftMenu) {
        giftCounts.add(giftMenu);
    }

    public int calculateTotalBenefit() {
        return benefitPrices.extractTotalBenefit();
    }
}
