package christmas.controller;

import christmas.domain.event.EventInfo;
import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;
import christmas.dto.PaymentDto;
import christmas.service.EventPlannerService;
import christmas.service.EventPlannerServiceImpl;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.PrintHandler;

public class EventPlannerController {
    private static final EventPlannerService eventPlannerService = new EventPlannerServiceImpl();

    public static void runEventPlanner() {
        OutputView.printStartingInfoMessage();

        ChosenDateDto chosenDateDto = receiveDateInput();
        OrderDto orderDto = takeOrder();
        OutputView.printResultHead(chosenDateDto);
        PrintHandler.printOrderMenus(orderDto);

        PaymentDto paymentDto = eventPlannerService.computeTotalPayment(orderDto);
        PrintHandler.printTotalOrderPrice(paymentDto);

        EventInfo eventInfo = eventPlannerService.computeEventApplication(chosenDateDto, orderDto, paymentDto);
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

    private static OrderDto takeOrder() {
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
