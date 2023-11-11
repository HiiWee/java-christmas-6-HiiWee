package christmas;

import christmas.domain.WootecoRestaurantManager;
import christmas.domain.event.WootecoEventManager;
import christmas.dto.BenefitDetails;
import christmas.dto.ReservedResults;
import christmas.exception.ExceptionResolver;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {

    private final InputView inputView;
    private final OutputView outputView;
    private final WootecoRestaurantManager restaurantManager;
    private final WootecoEventManager eventManager;

    public EventPlanner(final InputView inputView, final OutputView outputView,
                        final WootecoRestaurantManager restaurantManager, final WootecoEventManager eventManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.restaurantManager = restaurantManager;
        this.eventManager = eventManager;
    }

    public void run() {
        inputReservationInfo();
        applyEventBenefit();
        printReservationResults();
        printBenefitDetails();
    }

    private void inputReservationInfo() {
        ExceptionResolver.resolveProcessWithInput(restaurantManager::addSelectedDate, inputView::inputVisitDate);
        ExceptionResolver.resolveProcessWithInput(restaurantManager::addSelectedMenus, inputView::inputMenus);
        restaurantManager.updateReservation();
    }

    private void applyEventBenefit() {
        eventManager.applyAllEvents(restaurantManager);
    }

    private void printReservationResults() {
        ReservedResults results = restaurantManager.createReservationResults();
        outputView.printReservationResults(results);
    }

    private void printBenefitDetails() {
        BenefitDetails benefitDetails = eventManager.createBenefitDetails();
        outputView.printBenefitDetails(benefitDetails);
    }
}
