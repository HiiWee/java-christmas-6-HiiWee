package christmas.domain.event;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;
import christmas.dto.BadgeResult;
import christmas.dto.BenefitDetails;
import christmas.dto.PaymentAmountResult;
import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.function.Supplier;

public class WootecoEventManager {

    private final EventJoinHistoryRepository eventJoinHistoryRepository;

    public WootecoEventManager(final EventJoinHistoryRepository eventJoinHistoryRepository) {
        this.eventJoinHistoryRepository = eventJoinHistoryRepository;
    }

    public void applyAllEvents(final Supplier<Reservation> reservationSupplier) {
        EventJoinHistory history = EventJoinHistory.getInstance();
        EventType.joinEvents(reservationSupplier.get(), history);
        eventJoinHistoryRepository.saveEventHistory(history);
    }

    public BenefitDetails createBenefitDetails() {
        EventJoinHistory history = findEventHistoryObject();
        return BenefitDetails.createFrom(history);
    }

    public PaymentAmountResult createPaymentAmount(final Supplier<Reservation> reservationSupplier) {
        EventJoinHistory history = findEventHistoryObject();
        return PaymentAmountResult.createFrom(history, reservationSupplier.get());
    }

    public BadgeResult selectEventBadge() {
        EventJoinHistory history = findEventHistoryObject();
        EventBadge badge = EventBadge.findBadge(history.calculateTotalBenefit());
        return BadgeResult.createFrom(badge);
    }

    private EventJoinHistory findEventHistoryObject() {
        return eventJoinHistoryRepository.findEventHistory()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_EVENT_HISTORY::create);
    }
}
