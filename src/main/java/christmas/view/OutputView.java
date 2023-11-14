package christmas.view;

import christmas.dto.ChosenDateDto;

import static christmas.message.InfoMessage.*;

public class OutputView {
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
}
