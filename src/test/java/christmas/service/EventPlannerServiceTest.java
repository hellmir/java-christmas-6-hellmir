package christmas.service;

import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;
import christmas.dto.OrderMenuDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static christmas.message.ErrorMessage.INVALID_DATE_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("주문 입력값을 통해 주문을 생성하고 주문 메뉴와 수량을 저장한다.")
    @ParameterizedTest
    @CsvSource({
            "'초코케이크-3,양송이수프-2,제로콜라-3'", "'샴페인-2,해산물파스타-5,타파스-1'", "'아이스크림-1,바비큐립-3,레드와인-7'"
    })
    void generateOrder(String orderInput) {
        // given
        List<String> koreanMenuNames = new ArrayList<>();
        List<Integer> menuQuantities = new ArrayList<>();
        setOrderInformation(orderInput, koreanMenuNames, menuQuantities);

        // when
        OrderDto orderDto = eventPlannerService.generateOrder(orderInput);

        // then
        List<OrderMenuDto> orderMenuDtos = orderDto.getOrderMenuDtos();

        assertThat(orderDto.getOrderMenuDtos()).hasSize(3).extracting("menuQuantity")
                .containsExactly(menuQuantities.get(0), menuQuantities.get(1), menuQuantities.get(2));

        checkMenuNames(orderMenuDtos, koreanMenuNames);
    }

    private void setOrderInformation(String orderInput, List<String> koreanMenuNames, List<Integer> menuQuantities) {
        String[] menus = orderInput.split(",");

        for (String menu : menus) {
            koreanMenuNames.add(menu.split("-")[0]);
            menuQuantities.add(Integer.parseInt(menu.split("-")[1]));
        }
    }

    private void checkMenuNames(List<OrderMenuDto> orderMenuDtos, List<String> koreanMenuNames) {
        assertThat(orderMenuDtos.get(0).getMenuInformation().getKoreanName()).isEqualTo(koreanMenuNames.get(0));
        assertThat(orderMenuDtos.get(1).getMenuInformation().getKoreanName()).isEqualTo(koreanMenuNames.get(1));
        assertThat(orderMenuDtos.get(2).getMenuInformation().getKoreanName()).isEqualTo(koreanMenuNames.get(2));
    }
}
