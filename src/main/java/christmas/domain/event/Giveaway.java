package christmas.domain.event;

import christmas.domain.order.MenuInformation;
import christmas.dto.event.GiveawayDto;

import java.util.Objects;

public class Giveaway {
    private final MenuInformation giveaway;

    public Giveaway(MenuInformation giveaway) {
        this.giveaway = giveaway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giveaway giveaway1 = (Giveaway) o;
        return giveaway == giveaway1.giveaway;
    }

    @Override
    public int hashCode() {
        return Objects.hash(giveaway);
    }

    public GiveawayDto toDto() {
        return new GiveawayDto(giveaway);
    }
}
