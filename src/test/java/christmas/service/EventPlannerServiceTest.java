package christmas.service;

import christmas.domain.event.Badge;
import christmas.domain.event.ChosenDate;
import christmas.domain.order.MenuInformation;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.dto.event.ChosenDateDto;
import christmas.dto.event.EventInfoDto;
import christmas.dto.order.OrderDto;
import christmas.dto.order.OrderMenuDto;
import christmas.dto.order.PaymentDto;
import christmas.testutil.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static christmas.configuration.EventConfig.*;
import static christmas.domain.order.MenuInformation.CHAMPAGNE;
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

        int menuPrice1 = MENU1.getPrice() * QUANTITY1;
        int menuPrice2 = MENU2.getPrice() * QUANTITY2;
        int menuPrice3 = MENU3.getPrice() * QUANTITY3;
        int menuPrice4 = MENU4.getPrice() * QUANTITY4;

        // when
        PaymentDto paymentDto = eventPlannerService.computeTotalPayment(orderDto);

        // then
        assertThat(paymentDto.getPaymentAmount()).isEqualTo(menuPrice1 + menuPrice2 + menuPrice3 + menuPrice4);
    }

    @DisplayName("총주문금액이 10,000원 미만이면 이벤트가 적용되지 않는다.")
    @ParameterizedTest
    @CsvSource({"1", "1_000", "9_999"})
    void checkIsEventApplied(int paymentAmount) {
        // given
        PaymentDto paymentDto = new PaymentDto(paymentAmount);
        EventInfoDto eventInfoDto = eventPlannerService.generateEventInfo(paymentDto);

        // when
        boolean isEventApplied = eventPlannerService.checkIsEventApplied(paymentDto);
        MenuInformation giveaway = eventInfoDto.getGiveawayDto().getGiveaway();
        int christmasDiscountAmount = eventInfoDto.getChristmasDiscountDto().getDiscountDto().getDiscountAmount();
        int weekdayDiscountAmount = eventInfoDto.getWeekdayDiscountDto().getDiscountDto().getDiscountAmount();
        int weekendDiscountAmount = eventInfoDto.getWeekendDiscountDto().getDiscountDto().getDiscountAmount();
        Badge badge = eventInfoDto.getBadge();

        // then
        checkEventComponentValues(isEventApplied, giveaway, christmasDiscountAmount,
                weekdayDiscountAmount, weekendDiscountAmount, badge);
    }

    @DisplayName("할인 전 총주문금액이 120,000원 이상이면 증정품으로 샴페인을 반환한다.")
    @ParameterizedTest
    @CsvSource({"120_000", "200_000", "1_000_000"})
    void computeGiveawayApplicationWithGiveawayAppliedAmountOrMore(int paymentAmount) {
        // given
        PaymentDto paymentDto = new PaymentDto(paymentAmount);
        EventInfoDto eventInfoDto = eventPlannerService.generateEventInfo(paymentDto);

        // when
        eventInfoDto = eventPlannerService.computeGiveawayApplication(eventInfoDto, paymentDto);
        MenuInformation giveaway = eventInfoDto.getGiveawayDto().getGiveaway();

        // then
        assertThat(giveaway).isEqualTo(CHAMPAGNE);
    }

    @DisplayName("할인 전 총주문금액이 120,000원 미만이면 증정품을 반환하지 않는다.")
    @ParameterizedTest
    @CsvSource({"10", "100", "119_999"})
    void omputeGiveawayApplicationUnderGiveawayAppliedAmount(int paymentAmount) {
        // given
        PaymentDto paymentDto = new PaymentDto(paymentAmount);
        EventInfoDto eventInfoDto = eventPlannerService.generateEventInfo(paymentDto);

        // when
        eventInfoDto = eventPlannerService.computeGiveawayApplication(eventInfoDto, paymentDto);
        MenuInformation giveaway = eventInfoDto.getGiveawayDto().getGiveaway();

        // then
        assertThat(giveaway.isNone()).isTrue();
    }

    @DisplayName("25일 이전의 날짜와 할인 가능한 예상 결제 금액을 전송하면 크리스마스 디데이 이벤트가 적용된다.")
    @ParameterizedTest
    @CsvSource({"1, 30_000",
            "10, 10_000",
            "15, 119_999",
            "25, 120_000"})
    void computeChristmasDiscountApplicationWithTwentyFifthOrEarlierAndDiscountAvailableAmount
            (String chosenDateInput, int paymentAmount) {
        // given
        EventInfoDto eventInfoDto = setEventInfoDto(paymentAmount);
        ChosenDateDto chosenDateDto = ChosenDate.from(chosenDateInput).toDto();
        int discountAmount = BASIC_CHRISTMAS_DISCOUNT_AMOUNT
                + CHRISTMAS_DISCOUNT_INCREASE * (Integer.parseInt(chosenDateInput) - 1);

        // when
        eventInfoDto = eventPlannerService.computeChristmasDiscountApplication(eventInfoDto, chosenDateDto);
        int christmasDiscountAmount = eventInfoDto.getChristmasDiscountDto().getDiscountDto().getDiscountAmount();
        int paymentAmountAfterDiscount = eventInfoDto.getPaymentDto().getPaymentAmount();

        // then
        assertThat(christmasDiscountAmount).isEqualTo(discountAmount);
        assertThat(paymentAmountAfterDiscount).isEqualTo(paymentAmount - discountAmount);
    }

    @DisplayName("26일 이후의 날짜를 전송하면 크리스마스 디데이 이벤트가 적용되지 않는다.")
    @ParameterizedTest
    @CsvSource({"26, 30_000",
            "27, 10_000",
            "30, 119_999",
            "31, 120_000"})
    void computeChristmasDiscountApplicationWithDateAfterTwentySixth(String chosenDateInput, int paymentAmount) {
        // given
        EventInfoDto eventInfoDto = setEventInfoDto(paymentAmount);
        ChosenDateDto chosenDateDto = ChosenDate.from(chosenDateInput).toDto();

        // when
        eventInfoDto = eventPlannerService.computeChristmasDiscountApplication(eventInfoDto, chosenDateDto);
        int christmasDiscountAmount = eventInfoDto.getChristmasDiscountDto().getDiscountDto().getDiscountAmount();

        // then
        assertThat(christmasDiscountAmount).isEqualTo(0);
    }

    @DisplayName("전송된 날짜가 평일이면 디저트를 정해진 가격만큼 할인한다.")
    @ParameterizedTest
    @CsvSource({
            "3, 30_000",
            "14, 24_000",
            "31, 30_000"
    })
    void computeEventApplicationWhenWeekday(String chosenDateInput, int paymentAmount) {
        // given
        EventInfoDto eventInfoDto = setEventInfoDto(paymentAmount);
        ChosenDateDto chosenDateDto = ChosenDate.from(chosenDateInput).toDto();
        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();
        OrderDto orderDto = new Order(orderMenus).toDto();
        int discountAmount = countDessert(orderMenus) * WEEKDAY_DISCOUNT_AMOUNT;

        // when
        eventInfoDto = eventPlannerService.computeDayOfWeekDiscount(eventInfoDto, chosenDateDto, orderDto);
        int weekdayDiscountAmount = eventInfoDto.getWeekdayDiscountDto().getDiscountDto().getDiscountAmount();
        int paymentAmountAfterDiscount = eventInfoDto.getPaymentDto().getPaymentAmount();

        // then
        assertThat(weekdayDiscountAmount).isEqualTo(discountAmount);
        assertThat(paymentAmountAfterDiscount).isEqualTo(paymentAmount - discountAmount);
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

    private EventInfoDto setEventInfoDto(int paymentAmount) {
        PaymentDto paymentDto = new PaymentDto(paymentAmount);
        return eventPlannerService.generateEventInfo(paymentDto);
    }

    private void checkEventComponentValues(boolean isEventApplied, MenuInformation giveaway,
                                           int christmasDiscountAmount, int weekdayDiscountAmount,
                                           int weekendDiscountAmount, Badge badge) {
        assertThat(isEventApplied).isFalse();
        assertThat(giveaway.isNone()).isTrue();
        assertThat(christmasDiscountAmount).isEqualTo(0);
        assertThat(weekdayDiscountAmount).isEqualTo(0);
        assertThat(weekendDiscountAmount).isEqualTo(0);
        assertThat(badge.isNone()).isTrue();
    }

    private int countDessert(List<OrderMenu> orderMenus) {
        int dessertCount = 0;

        for (OrderMenu orderMenu : orderMenus) {
            if (orderMenu.isDessert()) {
                ++dessertCount;
            }
        }

        return dessertCount;
    }
}
