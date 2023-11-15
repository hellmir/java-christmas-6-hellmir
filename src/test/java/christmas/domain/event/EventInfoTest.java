package christmas.domain.event;

import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.Payment;
import christmas.testutil.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static christmas.configuration.EventConfig.*;
import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static christmas.message.ErrorMessage.PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EventInfoTest {
    @DisplayName("동일한 예상 결제 금액을 전송하면 동등한 EventInfo 인스턴스를 생성한다.")
    @Test
    void create() {
        // given
        Payment payment = new Payment(EVENT_APPLIED_AMOUNT);

        // when
        EventInfo eventInfo1 = new EventInfo(payment);
        EventInfo eventInfo2 = new EventInfo(payment);

        // then
        assertThat(eventInfo1).isEqualTo(eventInfo2);
    }

    @DisplayName("증정 이벤트가 적용되면 증정품이 담긴 Giveaway 객체를 생성한다.")
    @ParameterizedTest
    @CsvSource({"120_000", "123_456", "1_000_000"})
    void checkGiveawayApplicationWithApplicablePayment(int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo1 = new EventInfo(payment);
        EventInfo eventInfo2 = new EventInfo(payment);

        // when
        eventInfo1.updateGiveawayApplication(payment);

        // then
        assertThat(eventInfo1).isNotEqualTo(eventInfo2);
    }

    @DisplayName("증정 이벤트가 적용되지 않으면 증정품이 담긴 Giveaway 객체를 생성하지 않는다.")
    @ParameterizedTest
    @CsvSource({"119_999", "0", "10_000"})
    void checkGiveawayApplicationWithNotApplicablePayment(int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo1 = new EventInfo(payment);
        EventInfo eventInfo2 = new EventInfo(payment);

        // when
        eventInfo1.updateGiveawayApplication(payment);

        // then
        assertThat(eventInfo1).isEqualTo(eventInfo2);
    }

    @DisplayName("25 이하의 날짜와 할인 가능한 예상 결제 금액을 전송하면 크리스마스 디데이 이벤트가 적용된다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1_000",
            "15, 2_400",
            "25, 30_000"
    })
    void updateChristmasDiscountWithTwentyFifthOrEarlierAndDiscountAvailableAmount
            (String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo1 = new EventInfo(payment);
        EventInfo eventInfo2 = new EventInfo(payment);

        // when
        eventInfo1.updateChristmasDiscount(ChosenDate.from(chosenDateInput));

        // then
        assertThat(eventInfo1).isNotEqualTo(eventInfo2);
    }

    @DisplayName("25 이상의 날짜를 전송하면 크리스마스 디데이 이벤트가 적용되지 않는다.")
    @ParameterizedTest
    @CsvSource({
            "26, 1_000",
            "29, 2_400",
            "31, 30_000"
    })
    void updateChristmasDiscountWithDateAfterTwentySixth(String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo1 = new EventInfo(payment);
        EventInfo eventInfo2 = new EventInfo(payment);

        // when
        eventInfo1.updateChristmasDiscount(ChosenDate.from(chosenDateInput));

        // then
        assertThat(eventInfo1).isEqualTo(eventInfo2);
    }

    @DisplayName("할인 불가능한 예상 결제 금액을 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 999",
            "15, 2_399",
            "25, 1_000"
    })
    void updateChristmasDiscountWithDiscountUnavailableAmount(String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo = new EventInfo(payment);
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);
        int discountAmount = TestObjectFactory.computeChristmasDiscountAmount(chosenDateInput);

        // when, then
        assertThatThrownBy(() -> eventInfo.updateChristmasDiscount(chosenDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION
                        + (paymentAmount - discountAmount));
    }

    @DisplayName("전송된 날짜가 평일이면 디저트를 할인한다.")
    @ParameterizedTest
    @CsvSource({
            "3, 3_000",
            "14, 2_400",
            "31, 30_000"
    })
    void updateDayOfWeekDiscountWhenWeekday(String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo = new EventInfo(payment);
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);

        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();
        Order order = new Order(orderMenus);

        // when
        eventInfo.updateDayOfWeekDiscount(chosenDate, order);

        // then
        assertThat(eventInfo).isNotEqualTo(new EventInfo(payment));
    }

    @DisplayName("전송된 날짜가 평일이고, 할인 불가능한 예상 결제 금액을 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "3, 1000",
            "14, 2_022",
            "31, 1"
    })
    void updateDayOfWeekDiscountWithDiscountUnavailableAmountWhenWeekday(String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo = new EventInfo(payment);
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);

        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();
        Order order = new Order(orderMenus);
        int dessertCount = TestObjectFactory.countDessert(orderMenus);
        int discountAmount = dessertCount * WEEKDAY_DISCOUNT_AMOUNT;

        // when, then
        assertThatThrownBy(() -> eventInfo.updateDayOfWeekDiscount(chosenDate, order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION
                        + (paymentAmount - discountAmount));
    }

    @DisplayName("전송된 날짜가 주말이면 메인 메뉴를 할인한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 3_000",
            "16, 2_400",
            "30, 30_000"
    })
    void updateDayOfWeekDiscountWhenWeekend(String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo = new EventInfo(payment);
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);

        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();
        Order order = new Order(orderMenus);

        // when
        eventInfo.updateDayOfWeekDiscount(chosenDate, order);

        // then
        assertThat(eventInfo).isNotEqualTo(new EventInfo(payment));
    }

    @DisplayName("전송된 날짜가 주말이고, 할인 불가능한 예상 결제 금액을 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1000",
            "16, 2_022",
            "30, 1"
    })
    void updateDayOfWeekDiscountWithDiscountUnavailableAmount(String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo = new EventInfo(payment);
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);

        List<OrderMenu> orderMenus = TestObjectFactory.setOrderMenus();
        Order order = new Order(orderMenus);
        int mainCount = TestObjectFactory.countMain(orderMenus);

        int discountAmount = mainCount * WEEKEND_DISCOUNT_AMOUNT;

        // when, then
        assertThatThrownBy(() -> eventInfo.updateDayOfWeekDiscount(chosenDate, order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION
                        + (paymentAmount - discountAmount));
    }

    @DisplayName("이벤트 달력에 별이 있는 날짜를 전송하면 할인한다.")
    @ParameterizedTest
    @CsvSource({
            "3, 3_000",
            "17, 2_400",
            "31, 30_000"
    })
    void updateSpecialDiscount(String chosenDateInput, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo = new EventInfo(payment);
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);

        // when
        eventInfo.updateSpecialDiscount(chosenDate);

        // then
        assertThat(eventInfo).isNotEqualTo(new EventInfo(payment));
    }
}
