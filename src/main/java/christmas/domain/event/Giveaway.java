package christmas.domain.event;

import christmas.domain.order.MenuInformation;

public class Giveaway {
    private final MenuInformation giveaway;

    public Giveaway(MenuInformation giveaway) {
        this.giveaway = giveaway;
    }
}
