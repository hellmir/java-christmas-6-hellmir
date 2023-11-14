package christmas.service;

import christmas.domain.event.ChosenDate;
import christmas.domain.order.MenuInformation;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.Payment;
import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;
import christmas.dto.PaymentDto;

import java.util.ArrayList;
import java.util.List;

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
        Order order = dtoToDomain(orderDto);
        Payment payment = new Payment(order.computeTotalOrderPrice());
        return payment.toDto();
    }

    private Order dtoToDomain(OrderDto orderDto) {
        List<OrderMenu> orderMenus = new ArrayList<>();

        for (int i = 0; i < orderDto.size(); i++) {
            MenuInformation menuInformation = orderDto.getOrderMenuDtos().get(i).getMenuInformation();
            orderMenus.add(new OrderMenu(menuInformation));
        }

        return Order.of(orderMenus);
    }

    private String[] parseOrderInput(String orderInput) {
        return orderInput.split(",");
    }
}
