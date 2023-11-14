package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {
    private static int PAYMENT = 10_000;

    @DisplayName("동일한 금액을 전송하면 동등한 Payment 인스턴스를 생성한다.")
    @Test
    void create() {
        // given, when
        Payment payment = new Payment(PAYMENT);

        // then
        assertThat(payment).isEqualTo(new Payment(PAYMENT));
    }
}