package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    public static void runEventPlanner() {
        OutputView.printStartingInfoMessage();
        receiveUserInput();
    }

    private static void receiveUserInput() {
        String chosenDateInput = InputView.readDate();
    }
}
