package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChosenDateTest {
    @DisplayName("동일한 날짜 입력값을 전송하면 동등한 ChosenDate 인스턴스를 생성한다.")
    @ParameterizedTest
    @CsvSource({
            "1", "10", "21", "31"
    })
    void from(String chosenDateInput) {
        // given, when
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);

        // then
        assertThat(chosenDate).isEqualTo(ChosenDate.from(chosenDateInput));
    }
}
