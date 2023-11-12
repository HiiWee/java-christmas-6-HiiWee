package christmas.domain.event;

public enum EventCondition {

    MIN_PARTICIPATION_RESERVED_PRICE(10_000);

    private final int value;

    EventCondition(final int value) {
        this.value = value;
    }

    public static boolean canParticipatePrice(final int price) {
        return MIN_PARTICIPATION_RESERVED_PRICE.value <= price;
    }
}
