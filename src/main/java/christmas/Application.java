package christmas;

import christmas.domain.BookingRepository;
import christmas.domain.WootecoRestaurantManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner(
                new InputView(),
                new OutputView(),
                new WootecoRestaurantManager(new BookingRepository()));
        eventPlanner.run();
    }
}
