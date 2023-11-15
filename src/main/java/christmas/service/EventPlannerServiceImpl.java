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
    public EventInfoDto generateEventInfo(PaymentDto paymentDto) {
        Payment payment = Payment.from(paymentDto);
        return new EventInfo(payment).toDto();
    }

    @Override
    public boolean checkIsEventApplied(PaymentDto paymentDto) {
        Payment payment = Payment.from(paymentDto);
        return payment.isEventApplied();
    }

    @Override
    public EventInfoDto computeGiveawayApplication(EventInfoDto eventInfoDto, PaymentDto paymentDto) {
        EventInfo eventInfo = EventInfo.from(eventInfoDto);
        Payment payment = Payment.from(paymentDto);

        eventInfo.updateGiveawayApplication(payment);

        return eventInfo.toDto();
    }

    @Override
    public EventInfoDto computeChristmasDiscountApplication(EventInfoDto eventInfoDto, ChosenDateDto chosenDateDto) {
        EventInfo eventInfo = EventInfo.from(eventInfoDto);
        ChosenDate chosenDate = ChosenDate.from(chosenDateDto);

        eventInfo.updateChristmasDiscount(chosenDate);

        return eventInfo.toDto();
    }

    @Override
    public EventInfoDto computeDayOfWeekDiscount
            (EventInfoDto eventInfoDto, ChosenDateDto chosenDateDto, OrderDto orderDto) {
        EventInfo eventInfo = EventInfo.from(eventInfoDto);
        ChosenDate chosenDate = ChosenDate.from(chosenDateDto);
        Order order = Order.from(orderDto);

        eventInfo.updateDayOfWeekDiscount(chosenDate, order);

        return eventInfo.toDto();
    }

    @Override
    public EventInfoDto computeSpecialDiscount(EventInfoDto eventInfoDto, ChosenDateDto chosenDateDto) {
        EventInfo eventInfo = EventInfo.from(eventInfoDto);
        ChosenDate chosenDate = ChosenDate.from(chosenDateDto);

        eventInfo.updateSpecialDiscount(chosenDate);

        return eventInfo.toDto();
    }

    @Override
    public EventInfoDto giveBadge(EventInfoDto eventInfoDto) {
        EventInfo eventInfo = EventInfo.from(eventInfoDto);

        eventInfo.giveBadge();

        return eventInfo.toDto();
    }

    private String[] parseOrderInput(String orderInput) {
        return orderInput.split(",");
    }
}
