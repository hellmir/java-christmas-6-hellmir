package christmas.dto.event;

import christmas.domain.order.MenuInformation;

public class GiveawayDto {
    private final MenuInformation menuInformation;

    public GiveawayDto(MenuInformation menuInformation) {
        this.menuInformation = menuInformation;
    }

    public MenuInformation getMenuInformation() {
        return menuInformation;
    }
}
