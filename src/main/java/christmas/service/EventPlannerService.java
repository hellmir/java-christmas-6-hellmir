package christmas.service;

import christmas.dto.event.ChosenDateDto;
import christmas.dto.event.EventInfoDto;
import christmas.dto.order.OrderDto;
import christmas.dto.order.PaymentDto;

public interface EventPlannerService {
    ChosenDateDto parseChosenDate(String chosenDateInput);

    OrderDto generateOrder(String orderInput);

    PaymentDto computeTotalPayment(OrderDto orderDto);


    boolean checkIsEventApplied(PaymentDto paymentDto);

    EventInfoDto generateEventInfo(PaymentDto paymentDto);

    EventInfoDto computeGiveawayApplication(EventInfoDto eventInfoDto, PaymentDto paymentDto);

    EventInfoDto computeChristmasDiscountApplication(EventInfoDto eventInfoDto, ChosenDateDto chosenDateDto);

    EventInfoDto computeDayOfWeekDiscount(EventInfoDto eventInfoDto, ChosenDateDto chosenDateDto, OrderDto orderDto);

    EventInfoDto computeSpecialDiscount(EventInfoDto eventInfoDto, ChosenDateDto chosenDateDto);

    EventInfoDto giveBadge(EventInfoDto eventInfoDto);
}
