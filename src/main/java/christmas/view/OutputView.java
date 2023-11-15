package christmas.view;

import christmas.domain.event.Badge;
import christmas.domain.order.MenuInformation;
import christmas.dto.event.ChosenDateDto;
import christmas.dto.order.OrderMenuDto;

import static christmas.message.InfoMessage.*;

public class OutputView {
    public static void printLineBreak() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message + "\n");
    }

    public static void printStartingInfoMessage() {
        System.out.println(STARTING_INFO_MESSAGE);
    }

    public static void printResultHead(ChosenDateDto chosenDateDto) {
        System.out.println(FIRST_RESULT_MESSAGE_HEAD
                + chosenDateDto.getChosenDate() + SECOND_RESULT_MESSAGE_HEAD + "\n");
    }

    public static void printOrderMenusHead() {
        System.out.println(ORDER_MENUS_MESSAGE_HEAD);
    }

    public static void printOrderMenuInformation(OrderMenuDto orderMenuDto) {
        System.out.printf("%s %d개\n",
                orderMenuDto.getMenuInformation().getKoreanName(), orderMenuDto.getMenuQuantity());
    }

    public static void printTotalOrderPriceHead() {
        System.out.println(TOTAL_ORDER_PRICE_MESSAGE_HEAD);
    }

    public static void printGiveawayMessageHead() {
        System.out.println(GIVEAWAY_MESSAGE_HEAD);
    }

    public static void printNoneMessage() {
        System.out.println(NONE + "\n");
    }

    public static void printGiveawayMessage(MenuInformation giveaway) {
        System.out.println(giveaway.getKoreanName() + " 1개\n");
    }

    public static void printBenefitListMessageHead() {
        System.out.println(BENEFIT_LIST_MESSAGE_HEAD);
    }

    public static void printChristmasBenefitMessage(String christmasBenefit) {
        System.out.println(CHRISTMAS_BENEFIT_MESSAGE + christmasBenefit);
    }

    public static void printWeekdayBenefitMessage(String weekdayBenefit) {
        System.out.println(WEEKDAY_BENEFIT_MESSAGE + weekdayBenefit);
    }

    public static void printWeekendBenefitMessage(String weekendBenefit) {
        System.out.println(WEEKEND_BENEFIT_MESSAGE + weekendBenefit);
    }

    public static void printSpecialBenefitMessage(String specialBenefit) {
        System.out.println(SPECIAL_BENEFIT_MESSAGE + specialBenefit);
    }

    public static void printGiveawayBenefitMessage(String giveawayBenefit) {
        System.out.println(GIVEAWAY_BENEFIT_MESSAGE + giveawayBenefit);
    }

    public static void printBenefitAmountMessageHead() {
        System.out.println(BENEFIT_AMOUNT_MESSAGE_HEAD);
    }

    public static void printBenefitAmountMessage(String benefitAmount) {
        System.out.println("-" + benefitAmount + "\n");
    }

    public static void printExpectedPaymentMessageHead() {
        System.out.println(EXPECTED_PAYMENT_MESSAGE_HEAD);
    }

    public static void printExpectedPaymentMessage(String convertMoneyFormatForView) {
        System.out.println(convertMoneyFormatForView + "\n");
    }

    public static void printBadgeMessageHead() {
        System.out.println(BADGE_MESSAGE_HEAD);
    }

    public static void printBadgeMessage(Badge badge) {
        System.out.println(badge.getKoreanName());
    }
}
