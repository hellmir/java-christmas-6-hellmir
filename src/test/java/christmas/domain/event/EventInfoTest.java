package christmas.domain.event;

import christmas.domain.order.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EventInfoTest {
    @DisplayName("동등한 이벤트 정보 객체를 생성할 수 있다.")
    @Test
    void create() {
        // given, when, then
        assertThat(new EventInfo()).isEqualTo(new EventInfo());

    }

    @DisplayName("증정 이벤트가 적용되면 증정품이 담긴 Giveaway 객체를 생성한다.")
    @ParameterizedTest
    @CsvSource({"120_000", "123_456", "1_000_000"})
    void checkGiveawayApplicationWithApplicablePayment(int payment) {
        // given
        Payment payment1 = new Payment(payment);
        EventInfo eventInfo = new EventInfo();

        // when
        eventInfo.updateGiveawayApplication(payment1);

        // then
        assertThat(eventInfo).isNotEqualTo(new EventInfo());
    }

    @DisplayName("증정 이벤트가 적용되지 않으면 증정품이 담긴 Giveaway 객체를 생성하지 않는다.")
    @ParameterizedTest
    @CsvSource({"119_999", "0", "10_000"})
    void checkGiveawayApplicationWithNotApplicablePayment(int payment) {
        // given
        Payment payment1 = new Payment(payment);
        EventInfo eventInfo = new EventInfo();

        // when
        eventInfo.updateGiveawayApplication(payment1);

        // then
        assertThat(eventInfo).isEqualTo(new EventInfo());
    }
}