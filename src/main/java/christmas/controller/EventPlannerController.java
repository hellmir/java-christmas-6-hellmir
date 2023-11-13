package christmas.controller;

import christmas.domain.Order;
import christmas.dto.ChosenDateDto;
import christmas.service.EventPlannerService;
import christmas.service.EventPlannerServiceImpl;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private static final EventPlannerService eventPlannerService = new EventPlannerServiceImpl();

    public static void runEventPlanner() {
        OutputView.printStartingInfoMessage();
        Order order = takeOrder();
        ChosenDateDto chosenDateDto = receiveDateInput();
    }

    private static ChosenDateDto receiveDateInput() {
        while (true) {
            try {
                String chosenDateInput = InputView.readDate();
                return eventPlannerService.parseChosenDate(chosenDateInput);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }

    private static Order takeOrder() {
        while (true) {
            try {
                String orderInput = InputView.readOrder();
                return eventPlannerService.generateOrder(orderInput);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }
}
