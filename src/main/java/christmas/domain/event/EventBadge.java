package christmas.domain.event;

public enum EventBadge {

    SANTA(20_000, "산타"),
    TREE(10_000, "트리"),
    STAR(5_000, "별");

    private final int winningPrice;
    private final String name;

    EventBadge(final int winningPrice, final String name) {
        this.winningPrice = winningPrice;
        this.name = name;
    }
}
