package christmas;

import christmas.domain.event.WootecoEventManager;
import christmas.domain.restaurant.WootecoRestaurantManager;
import christmas.dto.badge.BadgeResult;
import christmas.dto.benefitdetail.BenefitDetails;
import christmas.dto.payment.PaymentAmountResult;
import christmas.dto.reservation.ReservedResults;
import christmas.exception.ExceptionResolver;
import christmas.view.InputViewable;
import christmas.view.OutputView;

public class EventPlanner {

    private final InputViewable inputView;
    private final OutputView outputView;
    private final WootecoRestaurantManager restaurantManager;
    private final WootecoEventManager eventManager;

    public EventPlanner(final InputViewable inputView, final OutputView outputView,
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
        printPaymentAmount();
        printEventBadge();
    }

    private void inputReservationInfo() {
        ExceptionResolver.resolveProcessAfterInput(inputView::inputVisitDate, restaurantManager::addSelectedDate);
        ExceptionResolver.resolveProcessAfterInput(inputView::inputMenus, restaurantManager::addSelectedMenus);
        restaurantManager.updateReservation();
    }

    private void applyEventBenefit() {
        eventManager.applyAllEvents(restaurantManager::findReservationObject);
    }

    private void printReservationResults() {
        ReservedResults results = restaurantManager.createReservationResults();
        outputView.printReservationResults(results);
    }

    private void printBenefitDetails() {
        BenefitDetails benefitDetails = eventManager.createBenefitDetails();
        outputView.printBenefitDetails(benefitDetails);
    }

    private void printPaymentAmount() {
        PaymentAmountResult paymentAmount = eventManager.createPaymentAmount(restaurantManager::findReservationObject);
        outputView.printPaymentAmount(paymentAmount);
    }

    private void printEventBadge() {
        BadgeResult badgeResult = eventManager.selectEventBadge();
        outputView.printEventBadge(badgeResult);
    }
}
