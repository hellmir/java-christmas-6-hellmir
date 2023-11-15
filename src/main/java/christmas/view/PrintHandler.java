package christmas.view;

import christmas.dto.order.OrderDto;
import christmas.dto.order.OrderMenuDto;
import christmas.dto.order.PaymentDto;
import christmas.utility.FormatConverter;

public class PrintHandler {
    public static void printOrderMenus(OrderDto orderDto) {
        OutputView.printOrderMenusHead();

        for (OrderMenuDto orderMenuDto : orderDto.getOrderMenuDtos()) {
            OutputView.printOrderMenuInformation(orderMenuDto);
        }

        OutputView.printLineBreak();
    }

    public static void printTotalOrderPrice(PaymentDto paymentDto) {
        OutputView.printTotalOrderPriceHead();
        String paymentToPrint = FormatConverter.convertMoneyFormatForView(paymentDto.getPayment());
        OutputView.printMessage(paymentToPrint);
    }
}
