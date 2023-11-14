package christmas.testutil;

import christmas.domain.order.OrderMenu;

import java.util.ArrayList;
import java.util.List;

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
}
