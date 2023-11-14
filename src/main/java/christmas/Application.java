package christmas;

import christmas.domain.event.EventManageRepository;
import christmas.domain.event.WootecoEventManager;
import christmas.domain.restaurant.BookingRepository;
import christmas.domain.restaurant.WootecoRestaurantManager;
import christmas.view.InputView;
import christmas.view.InputViewProxy;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner(
                new InputViewProxy(new InputView()),
                new OutputView(),
                new WootecoRestaurantManager(new BookingRepository()),
                new WootecoEventManager(new EventManageRepository()));
        eventPlanner.run();
    }
}
