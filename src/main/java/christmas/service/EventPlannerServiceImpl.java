package christmas.service;

import christmas.domain.event.ChosenDate;
import christmas.domain.order.Order;
import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;

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

    private String[] parseOrderInput(String orderInput) {
        return orderInput.split(",");
    }
}
