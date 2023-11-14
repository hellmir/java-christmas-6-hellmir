package christmas.validation;

import christmas.domain.order.MenuInformation;
import christmas.domain.order.OrderMenu;

import java.util.List;

import static christmas.message.ErrorMessage.*;

public class InputFormatValidator {
    public static void validateChosenDate(String chosenDateInput) {
        validateChosenDateNumberFormat(chosenDateInput);

        int chosenDate = Integer.parseInt(chosenDateInput);
        validateChosenDateRange(chosenDate);
    }


    public static MenuInformation validateMenuName(String koreanMenuName) {
        for (MenuInformation menuInformation : MenuInformation.values()) {
            if (koreanMenuName.equals(menuInformation.getKoreanName())) {
                return menuInformation;
            }
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    public static void validateMenuQuantityFormat(String menuQuantity) {
        if (menuQuantity.matches("[1-9][0-9]*")) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    public static void validateDuplicateOrderMenu(List<OrderMenu> orderMenus, OrderMenu orderMenu) {
        for (OrderMenu addedOrderMenu : orderMenus) {
            matchTwoMenus(orderMenu, addedOrderMenu);
        }
    }

    private static void validateChosenDateNumberFormat(String chosenDateInput) {
        if (chosenDateInput.matches("[1-9][0-9]*")) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + INVALID_DATE_EXCEPTION);
    }

    private static void validateChosenDateRange(int chosenDate) {
        if (1 <= chosenDate && chosenDate <= 31) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + INVALID_DATE_EXCEPTION);
    }

    private static void matchTwoMenus(OrderMenu orderMenu, OrderMenu addedOrderMenu) {
        if (!orderMenu.isSameMenu(addedOrderMenu)) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }
}
