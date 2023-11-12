package christmas.dto;

import christmas.domain.event.eventhistory.EventBenefitPrices;
import java.util.Map;
import java.util.stream.Collectors;

public record BenefitPriceResults(Map<String, Integer> benefitPrices) {

    private static final String MESSAGE_FORMAT = "%s: -%,d원";
    private static final String DELIMITER = "\n";

    public static BenefitPriceResults createFrom(final EventBenefitPrices benefitPrices) {
        return new BenefitPriceResults(benefitPrices.convertBenefitPricesWithName());
    }

    public String createMessage() {
        return benefitPrices.entrySet()
                .stream()
                .map(entry -> String.format(MESSAGE_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(DELIMITER));
    }

    public int getTotalBenefit() {
        return benefitPrices.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
