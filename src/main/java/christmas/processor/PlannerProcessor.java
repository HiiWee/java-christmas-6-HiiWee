package christmas.processor;

import java.util.function.Consumer;
import java.util.stream.Stream;

public enum PlannerProcessor {

    RESERVE_RESTAURANT(EventPlanner::inputReservationInfo),
    APPLY_EVENTS(EventPlanner::applyEventBenefit),
    PRINT_RESERVED_INFO(EventPlanner::printReservedResults),
    PRINT_BENEFIT_DETAILS(EventPlanner::printBenefitDetails),
    PRINT_PAYMENT_AMOUNT(EventPlanner::printPaymentAmount),
    PRINT_EVENT_BADGE(EventPlanner::printEventBadge);

    private static final String NOT_FOUND_PROCESS_FOR_EXECUTE = "[ERROR] 실행할 수 있는 프로세스가 없습니다.";
    private static final Consumer<EventPlanner> processCombiner;

    static {
        processCombiner = Stream.of(values())
                .map(processor -> processor.process)
                .reduce(Consumer::andThen)
                .orElseThrow(() -> new IllegalStateException(NOT_FOUND_PROCESS_FOR_EXECUTE));
    }

    private final Consumer<EventPlanner> process;

    PlannerProcessor(final Consumer<EventPlanner> process) {
        this.process = process;
    }

    public static void run(final EventPlanner eventPlanner) {
        processCombiner.accept(eventPlanner);
    }
}
