package christmas.dto;

import christmas.domain.event.history.EventBenefitPrices;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record ResultBenefitPrices(Map<String, Integer> benefitPrices) {

    private static final String MESSAGE_FORMAT = "%s: -%,d원";
    private static final String DELIMITER = "\n";

    public static ResultBenefitPrices createFrom(final EventBenefitPrices benefitPrices) {
        return new ResultBenefitPrices(
                benefitPrices.events()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().getEventName(),
                                Entry::getValue
                        ))
        );
    }

    public String createMessage() {
        return benefitPrices.entrySet()
                .stream()
                .filter(entry -> hasBenefitPrice(entry.getValue()))
                .map(entry -> String.format(MESSAGE_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(DELIMITER));
    }

    private static boolean hasBenefitPrice(final int currentEventBenefit) {
        return currentEventBenefit > 0;
    }

    public int getTotalBenefit() {
        return benefitPrices.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
