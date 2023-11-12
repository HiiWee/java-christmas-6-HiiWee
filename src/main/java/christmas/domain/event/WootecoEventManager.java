package christmas.domain.event;

import christmas.domain.event.eventhistory.EventParticipationHistory;
import christmas.domain.restaurant.reservation.Reservation;
import christmas.dto.BadgeResult;
import christmas.dto.BenefitDetails;
import christmas.dto.PaymentAmountResult;
import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.function.Supplier;

public class WootecoEventManager {

    private final EventRepository eventRepository;

    public WootecoEventManager(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void applyAllEvents(final Supplier<Reservation> reservationSupplier) {
        EventParticipationHistory history = EventParticipationHistory.getInstance();
        EventType.joinEvents(reservationSupplier.get(), history);
        eventRepository.saveEventHistory(history);
    }

    public BenefitDetails createBenefitDetails() {
        EventParticipationHistory history = findEventHistoryObject();
        return BenefitDetails.createFrom(history);
    }

    public PaymentAmountResult createPaymentAmount(final Supplier<Reservation> reservationSupplier) {
        EventParticipationHistory history = findEventHistoryObject();
        return PaymentAmountResult.createFrom(history, reservationSupplier.get());
    }

    public BadgeResult selectEventBadge() {
        EventParticipationHistory history = findEventHistoryObject();
        EventBadge badge = EventBadge.findBadge(history.calculateTotalBenefit());
        return BadgeResult.createFrom(badge);
    }

    private EventParticipationHistory findEventHistoryObject() {
        return eventRepository.findEventHistory()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_EVENT_HISTORY::create);
    }
}
