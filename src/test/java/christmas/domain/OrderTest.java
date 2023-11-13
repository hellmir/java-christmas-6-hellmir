package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
