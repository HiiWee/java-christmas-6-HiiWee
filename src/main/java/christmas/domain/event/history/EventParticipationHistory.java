package christmas.domain.event.history;

import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;
import java.util.ArrayList;
import java.util.EnumMap;

public record EventParticipationHistory(EventBenefits benefits, EventGifts gifts) {

    public static EventParticipationHistory getInstance() {
        return new EventParticipationHistory(
                new EventBenefits(new EnumMap<>(EventType.class)),
                new EventGifts(new ArrayList<>())
        );
    }

    public void participateEvent(final EventType eventType, final int benefitAmount) {
        benefits.updateBenefit(eventType, benefitAmount);
    }

    public void addGift(final Menu giftMenu) {
        gifts.add(giftMenu);
    }

    public int calculateTotalBenefit() {
        return benefits.extractTotalBenefit();
    }
}
