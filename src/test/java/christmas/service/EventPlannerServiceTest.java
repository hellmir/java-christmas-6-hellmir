package christmas.service;

import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.PaymentDto;
import christmas.testutil.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static christmas.message.ErrorMessage.*;
import static christmas.testutil.TestConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class EventPlannerServiceTest {
    private EventPlannerService eventPlannerService = new EventPlannerServiceImpl();

    @DisplayName("유효한 날짜를 전송하면 해당 날짜를 가지는 인스턴스를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "1", "10", "21", "31"
    })
    void parseChosenDateWithValidDate(String chosenDateInput) {
        // given, when
        ChosenDateDto chosenDateDto = eventPlannerService.parseChosenDate(chosenDateInput);

        // then
        assertThat(chosenDateDto.getChosenDate()).isEqualTo(Integer.parseInt(chosenDateInput));
    }

    @DisplayName("유효하지 않은 날짜를 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "0", "32", "a3", "17b", "가8"
    })
    void parseChosenDateWithInValidDate(String chosenDateInput) {
        // given, when, then
        assertThatThrownBy(() -> eventPlannerService.parseChosenDate(chosenDateInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_DATE_EXCEPTION);
    }

    @DisplayName("주문 입력값을 통해 주문을 생성하고 주문 메뉴와 수량을 저장한다.")
    @ParameterizedTest
    @CsvSource({
            "'초코케이크-3,양송이수프-2,제로콜라-3'", "'샴페인-2,해산물파스타-5,타파스-1'", "'아이스크림-1,바비큐립-3,레드와인-7'"
    })
    void generateOrder(String orderInput) {
        // given
        List<String> koreanMenuNames = new ArrayList<>();
        List<Integer> menuQuantities = new ArrayList<>();
        setOrderInformation(orderInput, koreanMenuNames, menuQuantities);

        // when
        OrderDto orderDto = eventPlannerService.generateOrder(orderInput);

        // then
        List<OrderMenuDto> orderMenuDtos = orderDto.getOrderMenuDtos();

        assertThat(orderDto.getOrderMenuDtos()).hasSize(3).extracting("menuQuantity")
                .containsExactly(menuQuantities.get(0), menuQuantities.get(1), menuQuantities.get(2));

        checkMenuNames(orderMenuDtos, koreanMenuNames);
    }

    @DisplayName("메뉴판에 없는 메뉴를 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "'케이크-3,양송이수프-2,제로콜라-3'", "'샴페인-2,파스타-5,타파스-1'", "'아이스크림-1,수프-3,와인-7'"
    })
    void generateOrderWithNotExistentMenu(String orderInput) {
        // given, when, then
        assertThatThrownBy(() -> eventPlannerService.generateOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    @DisplayName("전송된 메뉴 개수가 1보다 적으면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "'초코케이크-0,양송이수프-2,제로콜라-3'", "'샴페인-2,해산물파스타-0,타파스-1'", "'아이스크림-0,바비큐립-3,레드와인-0'"
    })
    void generateOrderWithUnderOneQuantity(String orderInput) {
        // given, when, then
        assertThatThrownBy(() -> eventPlannerService.generateOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    @DisplayName("전송된 메뉴 형식이 예시와 다르면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "'초코케이크--3,양송이수프-2,제로콜라-3'", "'샴페인-2,해산물파스타5,타파스-1'", "'아이스크림-1,바비큐립-3,레드와인-07'"
    })
    void generateOrderWithInvalidFormat(String orderInput) {
        // given, when, then
        assertThatThrownBy(() -> eventPlannerService.generateOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    @DisplayName("중복된 메뉴를 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "'초코케이크-3,양송이수프-2,초코케이크-3'", "'해산물파스타-2,샴페인-5,샴페인-1'", "'레드와인-1,레드와인-3,레드와인-7'"
    })
    void generateOrderWithDuplicateMenu(String orderInput) {
        // given, when, then
        assertThatThrownBy(() -> eventPlannerService.generateOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    @DisplayName("음료만 포함된 메뉴를 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "'제로콜라-3,샴페인-2,레드와인-3'", "'샴페인-2,레드와인-5,제로콜라-1'", "'레드와인-1,샴페인-3,제로콜라-7'"
    })
    void generateOrderWithOnlyBeverages(String orderInput) {
        // given, when, then
        assertThatThrownBy(() -> eventPlannerService.generateOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + ONLY_BEVERAGES_ORDERED_EXCEPTION);
    }

    @DisplayName("20개를 초과하는 메뉴를 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "'초코케이크-3,양송이수프-2,제로콜라-16'", "'샴페인-27,해산물파스타-5,타파스-1'", "'아이스크림-1,바비큐립-52,레드와인-7'"
    })
    void generateOrderWithOverTwentyMenus(String orderInput) {
        // given, when, then
        assertThatThrownBy(() -> eventPlannerService.generateOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + MENU_QUANTITY_EXCEEDED_EXCEPTION);
    }

    @DisplayName("전송된 주문 정보를 토대로 총주문 금액을 계산한다.")
    @Test
    void computeTotalPayment() {
        // given
        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();
        Order order = new Order(orderMenus);
        OrderDto orderDto = order.toDto();

        // when
        PaymentDto paymentDto = eventPlannerService.computeTotalPayment(orderDto);

        // then
        assertThat(paymentDto.getPayment())
                .isEqualTo(MENU1.getPrice() + MENU2.getPrice() + MENU3.getPrice() + MENU4.getPrice());
    }

    private void setOrderInformation(String orderInput, List<String> koreanMenuNames, List<Integer> menuQuantities) {
        String[] menus = orderInput.split(",");

        for (String menu : menus) {
            koreanMenuNames.add(menu.split("-")[0]);
            menuQuantities.add(Integer.parseInt(menu.split("-")[1]));
        }
    }

    private void checkMenuNames(List<OrderMenuDto> orderMenuDtos, List<String> koreanMenuNames) {
        assertThat(orderMenuDtos.get(0).getMenuInformation().getKoreanName()).isEqualTo(koreanMenuNames.get(0));
        assertThat(orderMenuDtos.get(1).getMenuInformation().getKoreanName()).isEqualTo(koreanMenuNames.get(1));
        assertThat(orderMenuDtos.get(2).getMenuInformation().getKoreanName()).isEqualTo(koreanMenuNames.get(2));
    }
}
