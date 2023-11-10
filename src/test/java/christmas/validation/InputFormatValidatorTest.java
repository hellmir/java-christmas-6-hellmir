package christmas.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static christmas.message.ErrorMessage.INVALID_DATE_EXCEPTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputFormatValidatorTest {
    @DisplayName("유효한 날짜를 전송하면 IllegalArgumentException이 발생하지 않는다.")
    @ParameterizedTest
    @CsvSource({
            "1", "10", "21", "31"
    })
    void validateChosenDateWithValidDate(String chosenDateInput) {
        // given, when, then
        InputFormatValidator.validateChosenDate(chosenDateInput);
    }

    @DisplayName("유효하지 않은 날짜를 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "0", "32", "a3", "17b", "가8"
    })
    void validateChosenDateWithInValidDate(String chosenDateInput) {
        // given, when, then
        assertThatThrownBy(() -> InputFormatValidator.validateChosenDate(chosenDateInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_DATE_EXCEPTION);
    }
}