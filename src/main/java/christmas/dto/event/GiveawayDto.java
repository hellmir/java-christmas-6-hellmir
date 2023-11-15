package christmas.dto.event;

import christmas.domain.order.MenuInformation;

public class GiveawayDto {
    private final MenuInformation giveaway;

    public GiveawayDto(MenuInformation giveaway) {
        this.giveaway = giveaway;
    }

    public MenuInformation getGiveaway() {
        return giveaway;
    }
}
