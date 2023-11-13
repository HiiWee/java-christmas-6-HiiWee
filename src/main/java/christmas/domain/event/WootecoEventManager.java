package christmas.domain.event;

import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.restaurant.reservation.Reservation;
import christmas.dto.badge.BadgeResult;
import christmas.dto.benefitdetail.BenefitDetails;
import christmas.dto.payment.PaymentAmountResult;
import christmas.validator.domain.exception.DomainExceptionMessage;
import java.util.function.Supplier;

public class WootecoEventManager {

    private final EventRepository eventRepository;

    public WootecoEventManager(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void applyAllEvents(final Supplier<Reservation> reservationSupplier) {
        EventJoinHistory history = EventJoinHistory.getInstance();
        EventType.joinEvents(reservationSupplier.get(), history);
        eventRepository.saveEventHistory(history);
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
        return eventRepository.findEventHistory()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_EVENT_HISTORY::create);
    }
}
