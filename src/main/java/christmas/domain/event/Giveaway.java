package christmas.domain.event;

import christmas.domain.order.MenuInformation;
import christmas.dto.event.GiveawayDto;

public class Giveaway {
    private final MenuInformation giveaway;

    public Giveaway(MenuInformation giveaway) {
        this.giveaway = giveaway;
    }

    public GiveawayDto toDto() {
        return new GiveawayDto(giveaway);
    }
}
