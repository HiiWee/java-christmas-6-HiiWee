package christmas.domain.event.history;

import christmas.domain.event.EventType;
import christmas.domain.restaurant.menu.Menu;
import java.util.EnumMap;

public record EventJoinHistory(EventBenefitPrices benefitPrices, EventGifts eventGifts) {

    private static final int ZERO_WON = 0;

    public static EventJoinHistory getInstance() {
        return new EventJoinHistory(
                new EventBenefitPrices(new EnumMap<>(EventType.class)),
                new EventGifts(new EnumMap<>(Menu.class))
        );
    }

    public void addParticipatedEvent(final EventType eventType, final int benefitAmount) {
        if (benefitAmount > ZERO_WON) {
            benefitPrices.updateBenefit(eventType, benefitAmount);
        }
    }

    public void addFreeGift(final Menu giftMenu) {
        eventGifts.add(giftMenu);
        addParticipatedEvent(EventType.GIVING_EVENT, giftMenu.getPrice());
    }

    public int calculateTotalBenefit() {
        return benefitPrices.extractTotalBenefit();
    }

    public int calculateDiscountBenefit() {
        return benefitPrices.extractDiscountBenefit();
    }
}
