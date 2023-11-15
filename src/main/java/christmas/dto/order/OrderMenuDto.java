package christmas.dto.order;

import christmas.domain.order.MenuInformation;

public class OrderMenuDto {
    private final MenuInformation menuInformation;
    private int menuQuantity;

    public OrderMenuDto(MenuInformation menuInformation, int menuQuantity) {
        this.menuInformation = menuInformation;
        this.menuQuantity = menuQuantity;
    }

    public MenuInformation getMenuInformation() {
        return menuInformation;
    }

    public int getMenuQuantity() {
        return menuQuantity;
    }
}
