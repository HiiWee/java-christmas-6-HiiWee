package christmas;

import christmas.domain.WootecoRestaurantManager;
import christmas.dto.ReservedResults;
import christmas.exception.ExceptionResolver;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {

    private final InputView inputView;
    private final OutputView outputView;
    private final WootecoRestaurantManager manager;

    public EventPlanner(final InputView inputView, final OutputView outputView,
                        final WootecoRestaurantManager manager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.manager = manager;
    }

    public void run() {
        inputVisitDate();
        inputMenus();
        printReservationResults();
    }

    private void inputVisitDate() {
        ExceptionResolver.resolveProcessWithInput(manager::addSelectedDate, inputView::inputVisitDate);
    }

    private void inputMenus() {
        ExceptionResolver.resolveProcessWithInput(manager::addSelectedMenu, inputView::inputMenus);
    }

    private void printReservationResults() {
        ReservedResults results = manager.createReservationResults();
        outputView.printReservationResults(results);
    }
}
