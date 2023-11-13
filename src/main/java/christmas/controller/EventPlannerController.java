package christmas.controller;

import christmas.domain.ChosenDate;
import christmas.domain.Order;
import christmas.exception.ExceptionStatus;
import christmas.service.EventPlannerService;
import christmas.service.EventPlannerServiceImpl;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private static final EventPlannerService eventPlannerService = new EventPlannerServiceImpl();

    public static void runEventPlanner() {
        OutputView.printStartingInfoMessage();
        ChosenDate chosenDate = receiveDateInput();
        Order order = receiveOrderInput();
    }

    private static ChosenDate receiveDateInput() {
        String chosenDateInput = InputView.readDate();
        return eventPlannerService.parseChosenDate(chosenDateInput);
    }

    private static Order receiveOrderInput() {
        Order order;
        ExceptionStatus exceptionStatus;

        do {
            String orderInput = InputView.readOrder();
            order = new Order();
            exceptionStatus = eventPlannerService.takeOrder(orderInput, order);
        } while (exceptionStatus.isOccurred());

        return order;
    }
}
