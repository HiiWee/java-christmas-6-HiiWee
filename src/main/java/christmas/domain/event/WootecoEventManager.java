package christmas.domain.event;

import christmas.domain.WootecoRestaurantManager;
import christmas.domain.event.history.EventParticipationHistory;
import christmas.domain.reservation.Reservation;
import christmas.dto.BenefitDetails;
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

    private EventParticipationHistory findEventHistoryObject() {
        return eventRepository.findEventHistory()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_EVENT_HISTORY::create);
    }
}
