package christmas.view;

import christmas.domain.order.MenuInformation;
import christmas.dto.event.EventInfoDto;
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
        String paymentToPrint = FormatConverter.convertMoneyFormatForView(paymentDto.getPaymentAmount());
        OutputView.printMessage(paymentToPrint);
    }

    public static void printResultMessages(EventInfoDto eventInfoDto) {
        printGiveawayMessage(eventInfoDto);
        printBenefitListMessage(eventInfoDto);
    }

    private static void printGiveawayMessage(EventInfoDto eventInfoDto) {
        OutputView.printGiveawayMessageHead();
        MenuInformation giveaway = eventInfoDto.getGiveawayDto().getGiveaway();

        if (giveaway.isNone()) {
            OutputView.printNoneMessage();
            return;
        }

        OutputView.printGiveawayMessage(eventInfoDto.getGiveawayDto().getGiveaway());
    }
}
