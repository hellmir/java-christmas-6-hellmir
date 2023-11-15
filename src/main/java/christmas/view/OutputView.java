package christmas.view;

import christmas.dto.event.ChosenDateDto;
import christmas.dto.order.OrderMenuDto;

import static christmas.message.InfoMessage.*;

public class OutputView {
    public static void printLineBreak() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);
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
}
