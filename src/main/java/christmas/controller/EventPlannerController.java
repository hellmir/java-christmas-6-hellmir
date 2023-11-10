package christmas.controller;

import christmas.domain.ChosenDate;
import christmas.service.EventPlannerService;
import christmas.service.EventPlannerServiceImpl;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private static final EventPlannerService eventPlannerService = new EventPlannerServiceImpl();

    public static void runEventPlanner() {
        OutputView.printStartingInfoMessage();
        receiveUserInput();
    }

    private static void receiveUserInput() {
        String chosenDateInput = InputView.readDate();
        ChosenDate chosenDate = eventPlannerService.parseChosenDate(chosenDateInput);
    }
}
