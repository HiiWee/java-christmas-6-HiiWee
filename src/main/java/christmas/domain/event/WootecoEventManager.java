package christmas.domain.event;

import christmas.domain.event.eventhistory.EventParticipationHistory;
import christmas.domain.restaurant.WootecoRestaurantManager;
import christmas.domain.restaurant.reservation.Reservation;
import christmas.dto.BadgeResult;
import christmas.dto.BenefitDetails;
import christmas.dto.PaymentAmountResult;
import christmas.validator.domain.exception.DomainExceptionMessage;

public class WootecoEventManager {

    private final EventRepository eventRepository;

    public WootecoEventManager(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void applyAllEvents(final WootecoRestaurantManager restaurantManager) {
        Reservation reservation = restaurantManager.findReservationObject();
        EventParticipationHistory history = EventParticipationHistory.getInstance();
        EventType.joinEvents(reservation, history);
        applyBadge(history);
        eventRepository.saveEventHistory(history);
    }

    private void applyBadge(final EventParticipationHistory history) {
        int totalBenefit = history.calculateTotalBenefit();
        EventBadge badge = EventBadge.findBadge(totalBenefit);
        eventRepository.saveBadge(badge);
    }

    public BenefitDetails createBenefitDetails() {
        EventParticipationHistory history = findEventHistoryObject();
        return BenefitDetails.createFrom(history);
    }

    public PaymentAmountResult createPaymentAmount(final WootecoRestaurantManager restaurantManager) {
        Reservation reservation = restaurantManager.findReservationObject();
        int totalPrice = reservation.getTotalPrice();
        EventParticipationHistory history = findEventHistoryObject();
        int totalBenefit = history.calculateTotalBenefit();
        return new PaymentAmountResult(totalPrice - totalBenefit);
    }

    public BadgeResult selectEventBadge() {
        EventParticipationHistory history = findEventHistoryObject();
        int totalBenefit = history.calculateTotalBenefit();
        EventBadge badge = EventBadge.findBadge(totalBenefit);
        return BadgeResult.createFrom(badge);
    }

    private EventParticipationHistory findEventHistoryObject() {
        return eventRepository.findEventHistory()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_EVENT_HISTORY::create);
    }
}
