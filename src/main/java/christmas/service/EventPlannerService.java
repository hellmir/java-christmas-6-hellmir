package christmas.service;

import christmas.domain.event.EventInfo;
import christmas.dto.event.ChosenDateDto;
import christmas.dto.order.OrderDto;
import christmas.dto.order.PaymentDto;

public interface EventPlannerService {
    ChosenDateDto parseChosenDate(String chosenDateInput);

    OrderDto generateOrder(String orderInput);

    PaymentDto computeTotalPayment(OrderDto orderDto);

    EventInfo computeEventApplication(ChosenDateDto chosenDateDto, OrderDto orderDto, PaymentDto paymentDto);
}
