package christmas.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.exception.ExceptionStatus.NOT_OCCURRED;
import static christmas.exception.ExceptionStatus.OCCURRED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ExceptionHandlerTest {
    @DisplayName("유효한 날짜를 전송하면 ExceptionStatus의 NOT_OCCURRED 상태를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "1", "10", "21", "31"
    })
    void handleDateInputExceptionWithValidDate(String chosenDateInput) {
        // given, when
        ExceptionStatus exceptionStatus = ExceptionHandler.handleDateInputException(chosenDateInput);

        // then
        assertThat(exceptionStatus).isEqualTo(NOT_OCCURRED);
    }

    @DisplayName("유효하지 않은 날짜를 전송하면 ExceptionStatus의 OCCURRED 상태를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "0", "32", "a3", "17b", "가8"
    })
    void handleDateInputExceptionWithInValidDate(String chosenDateInput) {
        // given, when
        ExceptionStatus exceptionStatus = ExceptionHandler.handleDateInputException(chosenDateInput);

        // then
        assertThat(exceptionStatus).isEqualTo(OCCURRED);
    }
}