package christmas.domain.order;

import christmas.testutil.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.testutil.TestConstant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderTest {
    private static final String MENU_NAME = "샴페인";
    private static final String MENU_QUANTITY = "4";

    @DisplayName("동등한 주문 객체를 생성할 수 있다.")
    @Test
    void create() {
        // given, when, then
        assertThat(new Order()).isEqualTo(new Order());
    }

    @DisplayName("주문에 주문 메뉴를 추가할 수 있다.")
    @Test
    void addOrderMenu() {
        // given
        Order order1 = new Order();
        Order order2 = new Order();

        String[] orderMenuNameAndQuantity = {MENU_NAME, MENU_QUANTITY};

        // when
        order1.addOrderMenu(orderMenuNameAndQuantity);

        // then
        assertThat(order1).isNotEqualTo(order2);
    }

    @DisplayName("동일한 주문 메뉴 목록을 전송하면 동등한 Order 인스턴스를 생성한다.")
    @Test
    void of() {
        // given
        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();

        // when
        Order order = new Order(orderMenus);

        // then
        assertThat(order).isEqualTo(new Order(orderMenus));
    }

    @DisplayName("총주문 금액을 계산한다.")
    @Test
    void computeTotalOrderPrice() {
        // given
        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();
        Order order = new Order(orderMenus);

        int menuPrice1 = MENU1.getPrice() * QUANTITY1;
        int menuPrice2 = MENU2.getPrice() * QUANTITY2;
        int menuPrice3 = MENU3.getPrice() * QUANTITY3;
        int menuPrice4 = MENU4.getPrice() * QUANTITY4;

        // when
        int totalPrice = order.computeTotalOrderPrice();

        // then
        assertThat(totalPrice).isEqualTo(menuPrice1 + menuPrice2 + menuPrice3 + menuPrice4);
    }
}
