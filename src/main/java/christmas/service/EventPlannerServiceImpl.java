package christmas.service;

import christmas.domain.event.ChosenDate;
import christmas.domain.event.EventInfo;
import christmas.domain.order.Order;
import christmas.domain.order.Payment;
import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;
import christmas.dto.PaymentDto;

public class EventPlannerServiceImpl implements EventPlannerService {
    @Override
    public ChosenDateDto parseChosenDate(String chosenDateInput) {
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);
        return chosenDate.toDto();
    }

    @Override
    public OrderDto generateOrder(String orderInput) {
        String[] orderMenuNamesAndQuantities = parseOrderInput(orderInput);
        Order order = new Order();

        for (String orderMenuNameAndQuantity : orderMenuNamesAndQuantities) {
            order.addOrderMenu(orderMenuNameAndQuantity.split("-"));
        }

        order.validateOrder();

        return order.toDto();
    }

    @Override
    public PaymentDto computeTotalPayment(OrderDto orderDto) {
        Order order = Order.from(orderDto);
        Payment payment = new Payment(order.computeTotalOrderPrice());
        return payment.toDto();
    }

    @Override
    public EventInfo computeEventApplication(ChosenDateDto chosenDateDto, OrderDto orderDto, PaymentDto paymentDto) {
        Payment payment = Payment.from(paymentDto);
        EventInfo eventInfo = new EventInfo();

        if (!payment.isEventApplied()) {
            return eventInfo;
        }

        return null;
    }

    private String[] parseOrderInput(String orderInput) {
        return orderInput.split(",");
    }
}
