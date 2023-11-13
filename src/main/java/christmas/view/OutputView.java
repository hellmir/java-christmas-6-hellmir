package christmas.view;

import static christmas.message.InfoMessage.STARTING_INFO_MESSAGE;

public class OutputView {
    public static void printStartingInfoMessage() {
        System.out.println(STARTING_INFO_MESSAGE);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
