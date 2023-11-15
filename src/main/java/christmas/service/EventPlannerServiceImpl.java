package christmas.service;

import christmas.domain.event.ChosenDate;
import christmas.domain.event.EventInfo;
import christmas.domain.order.Order;
import christmas.domain.order.Payment;
import christmas.dto.event.ChosenDateDto;
import christmas.dto.event.EventInfoDto;
import christmas.dto.order.OrderDto;
import christmas.dto.order.PaymentDto;

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
    public EventInfoDto computeEventApplication(ChosenDateDto chosenDateDto, OrderDto orderDto, PaymentDto paymentDto) {
        ChosenDate chosenDate = ChosenDate.from(chosenDateDto);
        Order order = Order.from(orderDto);
        Payment payment = Payment.from(paymentDto);
        EventInfo eventInfo = new EventInfo(payment);
        if (!payment.isEventApplied()) {
            return eventInfo.toDto();
        }

        eventInfo.updateGiveawayApplication(payment);
        eventInfo.updateChristmasDiscount(chosenDate);

        return eventInfo.toDto();
    }

    private String[] parseOrderInput(String orderInput) {
        return orderInput.split(",");
    }
}
