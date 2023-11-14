package christmas.service;

import christmas.domain.event.EventInfo;
import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;
import christmas.dto.PaymentDto;

public interface EventPlannerService {
    ChosenDateDto parseChosenDate(String chosenDateInput);

    OrderDto generateOrder(String orderInput);

    PaymentDto computeTotalPayment(OrderDto orderDto);

    EventInfo computeEventApplication(ChosenDateDto chosenDateDto, OrderDto orderDto, PaymentDto paymentDto);
}
