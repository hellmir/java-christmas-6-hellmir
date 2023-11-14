package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static christmas.message.ErrorMessage.PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PaymentTest {
    @DisplayName("동일한 금액을 전송하면 동등한 Payment 인스턴스를 생성한다.")
    @ParameterizedTest
    @CsvSource({"1", "10", "30_000"})
    void create(int paymentAmount) {
        // given, when
        Payment payment = new Payment(paymentAmount);

        // then
        assertThat(payment).isEqualTo(new Payment(paymentAmount));
    }

    @DisplayName("0원 미만의 금액을 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({"-1", "-10", "-30_000"})
    void validate(int paymentAmount) {
        // given, when, then
        assertThatThrownBy(() -> new Payment(paymentAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION + paymentAmount);
    }
}
