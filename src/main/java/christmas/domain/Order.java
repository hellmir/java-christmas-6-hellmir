package christmas.domain;

import christmas.validation.InputFormatValidator;

import java.util.ArrayList;
import java.util.List;

import static christmas.configuration.OrderConfig.MAX_MENU_QUANTITY;
import static christmas.message.ErrorMessage.*;

public class Order {
    private List<OrderMenu> orderMenus;
    private int totalMenuQuantity;

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
        totalMenuQuantity += parsedMenuQuantity;

        orderMenus.add(orderMenu);
    }

    public void validateOrder() {
        validateTotalMenuQuantity();

        for (OrderMenu orderMenu : orderMenus) {
            boolean isBeverage = checkBeverage(orderMenu);
            if (!isBeverage) {
                return;
            }
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + ONLY_BEVERAGES_ORDERED_EXCEPTION);
    }

    private void validateTotalMenuQuantity() {
        if (totalMenuQuantity <= MAX_MENU_QUANTITY) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + MENU_QUANTITY_EXCESSED_EXCEPTION);
    }

    private boolean checkBeverage(OrderMenu orderMenu) {
        if (orderMenu.getMenuInformation().isBeverage()) {
            return true;
        }

        return false;
    }
}
