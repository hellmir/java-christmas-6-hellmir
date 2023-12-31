package christmas.testutil;

import christmas.domain.order.OrderMenu;

import java.util.ArrayList;
import java.util.List;

import static christmas.configuration.EventConfig.BASIC_CHRISTMAS_DISCOUNT_AMOUNT;
import static christmas.configuration.EventConfig.CHRISTMAS_DISCOUNT_INCREASE;
import static christmas.testutil.TestConstant.*;

public class TestObjectFactory {
    public static List<OrderMenu> setOrderMenus() {
        List<OrderMenu> orderMenus = new ArrayList<>();

        orderMenus.add(new OrderMenu(MENU1, QUANTITY1));
        orderMenus.add(new OrderMenu(MENU2, QUANTITY2));
        orderMenus.add(new OrderMenu(MENU3, QUANTITY3));
        orderMenus.add(new OrderMenu(MENU4, QUANTITY4));

        return orderMenus;
    }

    public static int computeChristmasDiscountAmount(String chosenDateInput) {
        return BASIC_CHRISTMAS_DISCOUNT_AMOUNT + CHRISTMAS_DISCOUNT_INCREASE * (Integer.parseInt(chosenDateInput) - 1);
    }

    public static int countDessert(List<OrderMenu> orderMenus) {
        int dessertCount = 0;

        for (OrderMenu orderMenu : orderMenus) {
            if (orderMenu.isDessert()) {
                ++dessertCount;
            }
        }

        return dessertCount;
    }

    public static int countMain(List<OrderMenu> orderMenus) {
        int mainCount = 0;

        for (OrderMenu orderMenu : orderMenus) {
            if (orderMenu.isDessert()) {
                ++mainCount;
            }
        }

        return mainCount;
    }
}
