package christmas.domain.event;

import christmas.domain.WootecoRestaurantManager;
import christmas.domain.event.history.EventParticipationHistory;
import christmas.domain.reservation.Reservation;

public class WootecoEventManager {

    private final EventRepository eventRepository;

    public WootecoEventManager(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void applyAllEvents(final WootecoRestaurantManager restaurantManager) {
        Reservation reservation = restaurantManager.findReservationObject();
        EventParticipationHistory history = EventParticipationHistory.getInstance();
        System.out.println(reservation.getReservedDate() + " " + reservation.getTotalPrice());
        EventType.joinEvents(reservation, history);
        eventRepository.saveEventHistory(history);
    }
}
