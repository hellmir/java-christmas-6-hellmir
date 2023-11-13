package christmas.domain;

import christmas.dto.OrderMenuDto;

public class OrderMenu {
    private final MenuInformation menuInformation;
    private int menuQuantity;

    public OrderMenu(MenuInformation menuInformation) {
        this.menuInformation = menuInformation;
    }

    public void addMenuQuantity(int menuQuantity) {
        this.menuQuantity += menuQuantity;
    }

    public boolean isSameMenu(OrderMenu orderMenu) {
        return menuInformation.equals(orderMenu.getMenuInformation());
    }

    public MenuInformation getMenuInformation() {
        return menuInformation;
    }

    public OrderMenuDto toDto() {
        return new OrderMenuDto(menuInformation, menuQuantity);
    }
}
