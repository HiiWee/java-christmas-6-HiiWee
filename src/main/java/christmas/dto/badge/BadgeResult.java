package christmas.dto.badge;

import christmas.domain.event.badge.EventBadge;

public record BadgeResult(String name) {

    public static BadgeResult createFrom(final EventBadge badge) {
        return new BadgeResult(badge.getName());
    }
}
