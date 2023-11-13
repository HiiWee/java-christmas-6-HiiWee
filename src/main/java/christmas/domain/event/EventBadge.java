package christmas.domain.event;

import java.util.Arrays;

public enum EventBadge {

    SANTA(20_000, "산타"),
    TREE(10_000, "트리"),
    STAR(5_000, "별"),
    NONE(Integer.MAX_VALUE, "없음");

    private final int winningPrice;
    private final String name;

    EventBadge(final int winningPrice, final String name) {
        this.winningPrice = winningPrice;
        this.name = name;
    }

    public static EventBadge findBadge(final int totalBenefit) {
        return Arrays.stream(values())
                .filter(eventBadge -> eventBadge.canGetBadge(totalBenefit))
                .findFirst()
                .orElse(NONE);
    }

    private boolean canGetBadge(final int benefit) {
        return benefit >= winningPrice;
    }

    public String getName() {
        return name;
    }
}
