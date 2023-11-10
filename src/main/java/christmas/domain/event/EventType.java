package christmas.domain.event;

import christmas.domain.date.DateType;

public enum EventType {

    CHRISTMAS_EVENT(DateType.CHRISTMAS),
    WEEKDAY_EVENT(DateType.WEEKDAY),
    WEEKEND_EVENT(DateType.WEEKEND),
    SPECIAL_EVENT(DateType.SPECIAL),
    GIVING_EVENT(DateType.NONE);

    private final DateType dateType;

    EventType(final DateType dateType) {
        this.dateType = dateType;
    }
}
