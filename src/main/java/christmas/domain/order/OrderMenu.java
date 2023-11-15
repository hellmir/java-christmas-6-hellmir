package christmas.domain.order;

import christmas.dto.order.OrderMenuDto;

public class OrderMenu {
    private final MenuInformation menuInformation;
    private int menuQuantity;

    public OrderMenu(MenuInformation menuInformation) {
        this.menuInformation = menuInformation;
    }

    public OrderMenu(MenuInformation menuInformation, int menuQuantity) {
        this.menuInformation = menuInformation;
        this.menuQuantity = menuQuantity;
    }

    public static OrderMenu from(OrderMenuDto orderMenuDto) {
        return new OrderMenu(orderMenuDto.getMenuInformation(), orderMenuDto.getMenuQuantity());
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

    public int computeMenuPrice() {
        return menuInformation.getPrice() * menuQuantity;
    }

    public boolean isDessert() {
        return menuInformation.isDessert();
    }
}
