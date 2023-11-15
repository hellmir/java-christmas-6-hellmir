package christmas.domain.event;

import christmas.domain.order.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.configuration.EventConfig.EVENT_APPLIED_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("25 이하의 날짜와 할인 가능한 금액을 전송하면 크리스마스 디데이 이벤트를 적용할 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1_000",
            "15, 2_400",
            "25, 30_000"
    })
    void updateChristmasDiscount(String chosenDate, int paymentAmount) {
        // given
        Payment payment = new Payment(paymentAmount);
        EventInfo eventInfo1 = new EventInfo(payment);
        EventInfo eventInfo2 = new EventInfo(payment);

        // when
        eventInfo1.updateChristmasDiscount(ChosenDate.from(chosenDate));

        // then
        assertThat(eventInfo1).isNotEqualTo(eventInfo2);
    }
}