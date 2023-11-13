package christmas.domain;

import christmas.validation.InputFormatValidator;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderMenu> orderMenus;

    public Order() {
        orderMenus = new ArrayList<>();
    }

    public void addOrderMenu(String[] orderMenuNameAndQuantity) {
        String koreanMenuName = orderMenuNameAndQuantity[0];
        MenuInformation menuInformation = InputFormatValidator.validateMenuName(koreanMenuName);

        String menuQuantity = orderMenuNameAndQuantity[1];
        InputFormatValidator.validateMenuQuantityFormat(menuQuantity);

        OrderMenu orderMenu = new OrderMenu(menuInformation);
        InputFormatValidator.validateDuplicateOrderMenu(orderMenus, orderMenu);

        int parsedMenuQuantity = Integer.parseInt(menuQuantity);
        orderMenu.addMenuQuantity(parsedMenuQuantity);

        orderMenus.add(orderMenu);
    }
}
