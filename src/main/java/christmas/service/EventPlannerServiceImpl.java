package christmas.service;

import christmas.domain.ChosenDate;
import christmas.domain.Order;
import christmas.dto.ChosenDateDto;

public class EventPlannerServiceImpl implements EventPlannerService {
    @Override
    public ChosenDateDto parseChosenDate(String chosenDateInput) {
        ChosenDate chosenDate = ChosenDate.from(chosenDateInput);
        return chosenDate.toDto();
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
