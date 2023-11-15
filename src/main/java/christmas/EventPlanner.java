package christmas;

import christmas.domain.event.WootecoEventManager;
import christmas.domain.restaurant.WootecoRestaurantManager;
import christmas.exception.ExceptionResolver;

public class EventPlanner {

    private final EventViewer eventViewer;
    private final WootecoRestaurantManager restaurantManager;
    private final WootecoEventManager eventManager;

    public EventPlanner(final EventViewer eventViewer, final WootecoRestaurantManager restaurantManager,
                        final WootecoEventManager eventManager) {
        this.eventViewer = eventViewer;
        this.restaurantManager = restaurantManager;
        this.eventManager = eventManager;
    }

    public void inputReservationInfo() {
        ExceptionResolver.resolveProcessAfterInput(eventViewer::inputVisitDate, restaurantManager::addSelectedDate);
        ExceptionResolver.resolveProcessAfterInput(eventViewer::inputMenus, restaurantManager::addSelectedMenus);
        restaurantManager.updateReservation();
    }

    public void applyEventBenefit() {
        eventManager.applyAllEvents(restaurantManager::findReservationObject);
    }

    public void printReservedResults() {
        eventViewer.printReservationResults(restaurantManager.createReservationResults());
    }

    public void printBenefitDetails() {
        eventViewer.printBenefitDetails(eventManager.createBenefitDetails());
    }

    public void printPaymentAmount() {
        eventViewer.printPaymentAmount(eventManager.createPaymentAmount(restaurantManager::findReservationObject));
    }

    public void printEventBadge() {
        eventViewer.printEventBadge(eventManager.selectEventBadge());
    }
}
