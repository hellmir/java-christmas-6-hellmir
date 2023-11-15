package christmas.service;

import christmas.dto.event.ChosenDateDto;
import christmas.dto.event.EventInfoDto;
import christmas.dto.order.OrderDto;
import christmas.dto.order.PaymentDto;

public interface EventPlannerService {
    ChosenDateDto parseChosenDate(String chosenDateInput);

    OrderDto generateOrder(String orderInput);

    PaymentDto computeTotalPayment(OrderDto orderDto);

    EventInfoDto computeEventApplication(ChosenDateDto chosenDateDto, OrderDto orderDto, PaymentDto paymentDto);
}
