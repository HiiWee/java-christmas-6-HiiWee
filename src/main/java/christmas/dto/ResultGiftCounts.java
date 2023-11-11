package christmas.dto;

import christmas.domain.event.history.EventGifts;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record ResultGiftCounts(Map<String, Integer> giftCounts) {

    private static final String MESSAGE_FORMAT = "%s %d개";
    private static final String DELIMITER = "\n";

    public static ResultGiftCounts createFrom(final EventGifts giftCounts) {
        return new ResultGiftCounts(
                giftCounts.giftCounts()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().getName(),
                                Entry::getValue
                        ))
        );
    }

    public String createMessage() {
        return giftCounts.entrySet()
                .stream()
                .filter(entry -> isNotEmptyGift(entry.getValue()))
                .map(entry -> String.format(MESSAGE_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(DELIMITER));
    }

    private boolean isNotEmptyGift(final int giftCount) {
        return giftCount > 0;
    }
}
