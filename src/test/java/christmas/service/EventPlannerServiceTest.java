package christmas.service;

import christmas.dto.ChosenDateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static christmas.message.ErrorMessage.INVALID_DATE_EXCEPTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
}
