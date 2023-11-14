package christmas.domain.event;

public enum EventCondition {

    MIN_EVENT_JOIN_PRICE(10_000);

    private final int value;

    EventCondition(final int value) {
        this.value = value;
    }

    public static boolean canJoinEventPrice(final int price) {
        return MIN_EVENT_JOIN_PRICE.value <= price;
    }
}
