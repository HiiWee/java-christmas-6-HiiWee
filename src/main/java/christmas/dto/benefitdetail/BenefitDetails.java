package christmas.dto.benefitdetail;

import christmas.domain.event.history.EventJoinHistory;
import java.util.function.Supplier;

public record BenefitDetails(BenefitPriceResults benefitPrices, FreeGiftResults giftCounts) {

    private static final String EMPTY_MESSAGE = "없음";
    private static final String TOTAL_BENEFIT_PRICE_FORMAT = "%,d원";

    public static BenefitDetails createFrom(final EventJoinHistory history) {
        BenefitPriceResults benefitPrices = BenefitPriceResults.createFrom(history.benefitPrices());
        FreeGiftResults giftCounts = FreeGiftResults.createFrom(history.eventGifts());
        return new BenefitDetails(benefitPrices, giftCounts);
    }

    public String createGiftMenuMessage() {
        return createMessage(giftCounts::createMessage);
    }

    public String createBenefitDetailsMessage() {
        return createMessage(benefitPrices::createMessage);
    }

    public String getTotalBenefitPriceMessage() {
        return String.format(TOTAL_BENEFIT_PRICE_FORMAT, -benefitPrices.getTotalBenefit());
    }

    private String createMessage(Supplier<String> supplier) {
        String message = supplier.get();
        if (message.trim().isEmpty()) {
            return EMPTY_MESSAGE;
        }
        return message;
    }
}
