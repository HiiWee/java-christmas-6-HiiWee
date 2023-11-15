package christmas;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Stream;

public enum PlannerProcessor {

    RESERVE_RESTAURANT(EventPlanner::inputReservationInfo),
    APPLY_EVENTS(EventPlanner::applyEventBenefit),
    PRINT_RESERVED_INFO(EventPlanner::printReservedResults),
    PRINT_BENEFIT_DETAILS(EventPlanner::printBenefitDetails),
    PRINT_PAYMENT_AMOUNT(EventPlanner::printPaymentAmount),
    PRINT_EVENT_BADGE(EventPlanner::printEventBadge);

    private static final Consumer<EventPlanner> processCombiner;

    static {
        processCombiner = Stream.of(values())
                .map(processor -> processor.process)
                .reduce(Consumer::andThen)
                .orElseThrow(NoSuchElementException::new);
    }

    private final Consumer<EventPlanner> process;

    PlannerProcessor(final Consumer<EventPlanner> process) {
        this.process = process;
    }

    public static void run(final EventPlanner eventPlanner) {
        processCombiner.accept(eventPlanner);
    }
}
