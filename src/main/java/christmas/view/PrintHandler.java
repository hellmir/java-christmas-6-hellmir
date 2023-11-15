package christmas.view;

import christmas.domain.order.MenuInformation;
import christmas.dto.event.EventInfoDto;
import christmas.dto.order.OrderDto;
import christmas.dto.order.OrderMenuDto;
import christmas.dto.order.PaymentDto;
import christmas.utility.FormatConverter;

import static christmas.configuration.EventConfig.EVENT_APPLIED_AMOUNT;

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
        printBenefitAmountMessage(eventInfoDto);
        printExpectedPaymentMessage(eventInfoDto);
        printBadgeMessage(eventInfoDto);
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

    private static void printBenefitListMessage(EventInfoDto eventInfoDto) {
        OutputView.printBenefitListMessageHead();

        int paymentAmount = eventInfoDto.getPaymentDto().getPaymentAmount();
        int benefitAmount = eventInfoDto.getBenefitDto().getBenefitAmount();
        int giveawayAmount = eventInfoDto.getGiveawayDto().getGiveaway().getPrice();

        if (paymentAmount + (benefitAmount - giveawayAmount) < EVENT_APPLIED_AMOUNT) {
            OutputView.printNoneMessage();
            return;
        }

        printLists(eventInfoDto);
    }

    private static void printLists(EventInfoDto eventInfoDto) {
        printChristmasBenefitMessage(eventInfoDto);
        printDayOfWeekBenefitMessage(eventInfoDto);
        printSpecialBenefitMessage(eventInfoDto);
        printGiveawayBenefitMessage(eventInfoDto);

        OutputView.printLineBreak();
    }

    private static void printChristmasBenefitMessage(EventInfoDto eventInfoDto) {
        int christmasDiscountAmount = eventInfoDto.getChristmasDiscountDto().getDiscountDto().getDiscountAmount();

        if (isChristmasDiscount(christmasDiscountAmount)) {
            OutputView.printChristmasBenefitMessage(FormatConverter.convertMoneyFormatForView(christmasDiscountAmount));
        }
    }

    private static void printDayOfWeekBenefitMessage(EventInfoDto eventInfoDto) {
        int weekdayDiscountAmount = eventInfoDto.getWeekdayDiscountDto().getDiscountDto().getDiscountAmount();

        if (isWeekdayDiscount(weekdayDiscountAmount)) {
            OutputView.printWeekdayBenefitMessage(FormatConverter.convertMoneyFormatForView(weekdayDiscountAmount));
            return;
        }

        int weekendDiscountAmount = eventInfoDto.getWeekendDiscountDto().getDiscountDto().getDiscountAmount();

        if (isWeekendDiscount(weekendDiscountAmount)) {
            OutputView.printWeekendBenefitMessage(FormatConverter.convertMoneyFormatForView(weekdayDiscountAmount));
        }
    }

    private static void printSpecialBenefitMessage(EventInfoDto eventInfoDto) {

        int specialDiscountAmount = eventInfoDto.getSpecialDiscountDto().getDiscountDto().getDiscountAmount();

        if (isSpecialDiscount(specialDiscountAmount)) {
            OutputView.printSpecialBenefitMessage(FormatConverter.convertMoneyFormatForView(specialDiscountAmount));
        }
    }

    private static void printGiveawayBenefitMessage(EventInfoDto eventInfoDto) {
        int giveawayAmount = eventInfoDto.getGiveawayDto().getGiveaway().getPrice();

        if (isGiveawayExists(giveawayAmount)) {
            OutputView.printGiveawayBenefitMessage(FormatConverter.convertMoneyFormatForView(giveawayAmount));
        }
    }


    private static boolean isChristmasDiscount(int christmasDiscountAmount) {
        return christmasDiscountAmount > 0;
    }

    private static boolean isWeekdayDiscount(int weekdayDiscountAmount) {
        return weekdayDiscountAmount > 0;
    }

    private static boolean isWeekendDiscount(int weekendDiscountAmount) {
        return weekendDiscountAmount > 0;
    }

    private static boolean isSpecialDiscount(int specialDiscountAmount) {
        return specialDiscountAmount > 0;
    }

    private static boolean isGiveawayExists(int giveawayAmount) {
        return giveawayAmount > 0;
    }

    private static void printBenefitAmountMessage(EventInfoDto eventInfoDto) {
        OutputView.printBenefitAmountMessageHead();
        int benefitAmount = eventInfoDto.getBenefitDto().getBenefitAmount();

        if (benefitAmount > 0) {
            OutputView.printBenefitAmountMessage(FormatConverter.convertMoneyFormatForView(benefitAmount));
        }
    }

    private static void printExpectedPaymentMessage(EventInfoDto eventInfoDto) {
        OutputView.printExpectedPaymentMessageHead();

        int paymentAmount = eventInfoDto.getPaymentDto().getPaymentAmount();
    }

    private static void printBadgeMessage(EventInfoDto eventInfoDto) {
        OutputView.printBadgeMessageHead();
        OutputView.printBadgeMessage(eventInfoDto.getBadge());
    }
}
