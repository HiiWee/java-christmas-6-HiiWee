package christmas.domain.event;

import christmas.domain.event.discount.ChristmasDiscountEvent;
import christmas.domain.event.discount.SpecialDiscountEvent;
import christmas.domain.event.discount.WeekdayDiscountEvent;
import christmas.domain.event.discount.WeekendDiscountEvent;
import christmas.domain.event.eventhistory.EventJoinHistory;
import christmas.domain.event.gift.ChampagneGiftEvent;
import christmas.domain.restaurant.date.DateType;
import christmas.domain.restaurant.reservation.Reservation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EventType {

    CHRISTMAS_EVENT("크리스마스 디데이 할인", DateType.CHRISTMAS, new ChristmasDiscountEvent()),
    WEEKDAY_EVENT("평일 할인", DateType.WEEKDAY, new WeekdayDiscountEvent()),
    WEEKEND_EVENT("주말 할인", DateType.WEEKEND, new WeekendDiscountEvent()),
    SPECIAL_EVENT("특별 할인", DateType.SPECIAL, new SpecialDiscountEvent()),
    GIVING_EVENT("증정 이벤트", DateType.NONE, new ChampagneGiftEvent());

    private static final Map<DateType, EventType> EVENT_TYPE_CACHE = Arrays.stream(values()).collect(
            Collectors.toMap(eventType -> eventType.dateType, Function.identity())
    );

    private final String eventName;
    private final DateType dateType;
    private final Event event;

    EventType(final String eventName, final DateType dateType, final Event event) {
        this.eventName = eventName;
        this.dateType = dateType;
        this.event = event;
    }

    public String getEventName() {
        return eventName;
    }

    public static boolean isNotGivingEvent(final EventType eventType) {
        return eventType != GIVING_EVENT;
    }

    public static List<EventType> findEventTypesFrom(final List<DateType> dateTypes) {
        return dateTypes.stream()
                .map(EVENT_TYPE_CACHE::get)
                .toList();
    }

    public static void joinEvents(final Reservation reservation, final EventJoinHistory history) {
        if (Event.canJoinAnyEvent(reservation)) {
            List<Event> events = getEvents();
            events.forEach(event -> event.participateEvent(history, reservation));
        }
    }

    private static List<Event> getEvents() {
        return Arrays.stream(values())
                .map(eventType -> eventType.event)
                .toList();
    }
}
