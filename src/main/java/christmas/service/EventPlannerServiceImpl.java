package christmas.service;

import christmas.domain.ChosenDate;
import christmas.domain.Order;

public class EventPlannerServiceImpl implements EventPlannerService {
    @Override
    public ChosenDate parseChosenDate(String chosenDateInput) {
        return ChosenDate.from(chosenDateInput);
    }

    @Override
    public Order generateOrder(String orderInput) {
        String[] orderMenuNamesAndQuantities = parseOrderInput(orderInput);
        Order order = new Order();

        for (String orderMenuNameAndQuantity : orderMenuNamesAndQuantities) {
            order.addOrderMenu(orderMenuNameAndQuantity.split("-"));
        }

        order.validateOrder();

        return order;
    }

    private String[] parseOrderInput(String orderInput) {
        return orderInput.split(",");
    }
}
