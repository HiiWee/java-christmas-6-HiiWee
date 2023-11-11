package christmas.dto;

import christmas.domain.event.EventBadge;

public record BadgeResult(String name) {

    public static BadgeResult createFrom(final EventBadge badge) {
        return new BadgeResult(badge.getName());
    }
}
