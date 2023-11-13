package christmas.validation;

import christmas.domain.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static christmas.domain.MenuInformation.*;
import static christmas.message.ErrorMessage.*;
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

    @DisplayName("메뉴판에 있는 메뉴를 전송하면 IllegalArgumentException이 발생하지 않는다.")
    @ParameterizedTest
    @CsvSource({
            "양송이수프", "크리스마스파스타", "제로콜라", "아이스크림"
    })
    void validateMenuNameWithExistentMenuName(String koreanMenuName) {
        // given, when, then
        InputFormatValidator.validateMenuName(koreanMenuName);
    }

    @DisplayName("메뉴판에 없는 메뉴를 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "케이크", "파스타", "수프", "와인"
    })
    void validateMenuNameWithNotExistentMenuName(String koreanMenuName) {
        // given, when, then
        assertThatThrownBy(() -> InputFormatValidator.validateMenuName(koreanMenuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    @DisplayName("양수 값을 전송하면 IllegalArgumentException이 발생하지 않는다.")
    @ParameterizedTest
    @CsvSource({
            "1", "5", "15", "20"
    })
    void validateMenuQuantityFormatWithPositiveNumber(String menuQuantity) {
        // given, when, then
        InputFormatValidator.validateMenuQuantityFormat(menuQuantity);
    }

    @DisplayName("양수가 아닌 값을 전송하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "0", "-3", "5가나", "abc"
    })
    void validateMenuQuantityFormat(String menuQuantity) {
        // given, when, then
        assertThatThrownBy(() -> InputFormatValidator.validateMenuQuantityFormat(menuQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }

    @DisplayName("서로 중복되지 않는 메뉴를 전송하면 IllegalArgumentException이 발생하지 않는다.")
    @Test
    void validateDuplicateOrderMenuWithNotDuplicatedMenu() {
        // given
        List<OrderMenu> orderMenus = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            orderMenus.add(new OrderMenu(BUTTON_MUSHROOM_SOUP));
            orderMenus.add(new OrderMenu(CHRISTMAS_PASTA));
            orderMenus.add(new OrderMenu(ZERO_SUGAR_COKE));
            orderMenus.add(new OrderMenu(ICE_CREAM));
        }

        OrderMenu orderMenu = new OrderMenu(T_BONE_STEAK);

        // when, then
        InputFormatValidator.validateDuplicateOrderMenu(orderMenus, orderMenu);
    }

    @DisplayName("서로 중복되는 메뉴를 전송하면 IllegalArgumentException이 발생한다.")
    @Test
    void validateDuplicateOrderMenuWithDuplicatedMenu() {
        // given
        List<OrderMenu> orderMenus = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            orderMenus.add(new OrderMenu(BUTTON_MUSHROOM_SOUP));
            orderMenus.add(new OrderMenu(CHRISTMAS_PASTA));
            orderMenus.add(new OrderMenu(ZERO_SUGAR_COKE));
            orderMenus.add(new OrderMenu(ICE_CREAM));
        }

        OrderMenu orderMenu = new OrderMenu(ZERO_SUGAR_COKE);

        // when, then
        assertThatThrownBy(() -> InputFormatValidator.validateDuplicateOrderMenu(orderMenus, orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_HEAD + INVALID_ORDER_EXCEPTION);
    }
}