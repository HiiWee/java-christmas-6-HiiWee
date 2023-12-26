package christmas.domain.event;

import christmas.domain.restaurant.date.DateType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EventType {

    CHRISTMAS_EVENT("크리스마스 디데이 할인", DateType.CHRISTMAS),
    WEEKDAY_EVENT("평일 할인", DateType.WEEKDAY),
    WEEKEND_EVENT("주말 할인", DateType.WEEKEND),
    SPECIAL_EVENT("특별 할인", DateType.SPECIAL),
    GIVING_EVENT("증정 이벤트", DateType.NONE);

    private static final Map<DateType, EventType> EVENT_TYPE_GROUPING_BY_DATE = Arrays.stream(values()).collect(
            Collectors.toMap(eventType -> eventType.dateType, Function.identity())
    );

    private final String name;
    private final DateType dateType;

    EventType(final String name, final DateType dateType) {
        this.name = name;
        this.dateType = dateType;
    }

    public static boolean isNotGivingEvent(final EventType eventType) {
        return eventType != GIVING_EVENT;
    }

    public static List<EventType> findEventTypesFrom(final List<DateType> dateTypes) {
        return dateTypes.stream()
                .map(EVENT_TYPE_GROUPING_BY_DATE::get)
                .toList();
    }

    public String getName() {
        return name;
    }
}
