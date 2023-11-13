package christmas.dto.benefitdetail;

import christmas.domain.event.eventhistory.EventGifts;
import java.util.Map;
import java.util.stream.Collectors;

public record FreeGiftResults(Map<String, Integer> giftCounts) {

    private static final String MESSAGE_FORMAT = "%s %d개";
    private static final String DELIMITER = "\n";

    public static FreeGiftResults createFrom(final EventGifts giftCounts) {
        return new FreeGiftResults(giftCounts.convertMenuToName());
    }

    public String createMessage() {
        return giftCounts.entrySet()
                .stream()
                .map(entry -> String.format(MESSAGE_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(DELIMITER));
    }
}
