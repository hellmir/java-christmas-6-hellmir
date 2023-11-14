package christmas.view;

import christmas.dto.OrderDto;
import christmas.dto.OrderMenuDto;

public class PrintHandler {
    public static void printOrderMenus(OrderDto orderDto) {
        OutputView.printOrderMenusHead();

        for (OrderMenuDto orderMenuDto : orderDto.getOrderMenuDtos()) {
            OutputView.printOrderMenuInformation(orderMenuDto);
        }

        OutputView.printLineBreak();
    }
}
