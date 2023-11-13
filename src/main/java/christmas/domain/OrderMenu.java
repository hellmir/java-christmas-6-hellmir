package christmas.domain;

public class OrderMenu {
    private final MenuInformation menuInformation;
    private int menuQuantity;

    public OrderMenu(MenuInformation menuInformation) {
        this.menuInformation = menuInformation;
    }

    public void addMenuQuantity(int menuQuantity) {
        ++menuQuantity;
    }

    public boolean isSameMenu(OrderMenu orderMenu) {
        return menuInformation.equals(orderMenu.getMenuInformation());
    }

    public MenuInformation getMenuInformation() {
        return menuInformation;
    }
}
