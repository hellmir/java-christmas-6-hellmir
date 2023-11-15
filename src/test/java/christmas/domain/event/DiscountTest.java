package christmas.domain.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.message.ErrorMessage.DISCOUNT_AMOUNT_UNDER_ZERO_EXCEPTION;
import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DiscountTest {
    @DisplayName("동일한 금액을 전송하면 동등한 Discount 인스턴스를 생성한다.")
    @ParameterizedTest
    @CsvSource({"0", "1", "10", "30_000"})
    void create(int discountAmount) {
        // given, when
        Discount discount = new Discount(discountAmount);

        // then
        assertThat(discount).isEqualTo(new Discount(discountAmount));
    }

    @DisplayName("0원 미만의 금액을 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({"-1", "-10", "-30_000"})
    void validate(int discountAmount) {
        // given, when, then
        assertThatThrownBy(() -> new Discount(discountAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + DISCOUNT_AMOUNT_UNDER_ZERO_EXCEPTION + discountAmount);
    }
}