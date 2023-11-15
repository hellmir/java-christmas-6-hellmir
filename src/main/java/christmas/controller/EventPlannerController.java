package christmas.controller;

import christmas.dto.event.ChosenDateDto;
import christmas.dto.event.EventInfoDto;
import christmas.dto.order.OrderDto;
import christmas.dto.order.PaymentDto;
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

        EventInfoDto eventInfoDto = computeEventApplications(chosenDateDto, orderDto, paymentDto);
        PrintHandler.printResultMessages(eventInfoDto);
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

    private static EventInfoDto computeEventApplications
            (ChosenDateDto chosenDateDto, OrderDto orderDto, PaymentDto paymentDto) {
        EventInfoDto eventInfoDto = eventPlannerService.generateEventInfo(paymentDto);
        boolean isEventApplied = eventPlannerService.checkIsEventApplied(paymentDto);

        if (!isEventApplied) {
            return eventInfoDto;
        }

        eventInfoDto = eventPlannerService.computeGiveawayApplication(eventInfoDto, paymentDto);
        eventInfoDto = eventPlannerService.computeChristmasDiscountApplication(eventInfoDto, chosenDateDto);
        eventInfoDto = eventPlannerService.computeDayOfWeekDiscount(eventInfoDto, chosenDateDto, orderDto);
        eventInfoDto = eventPlannerService.computeSpecialDiscount(eventInfoDto, chosenDateDto);
        eventInfoDto = eventPlannerService.giveBadge(eventInfoDto);

        return eventInfoDto;
    }
}
