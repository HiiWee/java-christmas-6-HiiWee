package christmas.dto;

import christmas.domain.event.history.EventParticipationHistory;

public record BenefitDetails(BenefitPriceResults benefitPrices, GiftCountResults giftCounts) {

    private static final String EMPTY_MESSAGE = "없음";
    private static final String TOTAL_BENEFIT_PRICE_FORMAT = "-%,d원";

    public static BenefitDetails createFrom(final EventParticipationHistory history) {
        BenefitPriceResults benefitPrices = BenefitPriceResults.createFrom(history.benefitPrices());
        GiftCountResults giftCounts = GiftCountResults.createFrom(history.giftCounts());
        return new BenefitDetails(benefitPrices, giftCounts);
    }

    public String createGiftMenuMessage() {
        String message = giftCounts.createMessage();
        if (message.trim().isEmpty()) {
            return EMPTY_MESSAGE;
        }
        return message;
    }

    public String createBenefitDetailsMessage() {
        String message = benefitPrices.createMessage();
        if (message.trim().isEmpty()) {
            return EMPTY_MESSAGE;
        }
        return message;
    }

    public String getTotalBenefitPriceMessage() {
        return String.format(TOTAL_BENEFIT_PRICE_FORMAT, benefitPrices.getTotalBenefit());
    }
}