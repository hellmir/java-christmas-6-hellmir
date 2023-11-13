package christmas.service;

import christmas.domain.ChosenDate;
import christmas.domain.Order;
import christmas.exception.ExceptionStatus;

public class EventPlannerServiceImpl implements EventPlannerService {
    @Override
    public ChosenDate parseChosenDate(String chosenDateInput) {
        return ChosenDate.from(chosenDateInput);
    }

    @Override
    public ExceptionStatus takeOrder(String orderInput, Order order) {
        String[] orderMenuNamesAndQuantities = parseOrderInput(orderInput);

        try {
            for (String orderMenuNameAndQuantity : orderMenuNamesAndQuantities) {
                order.addOrderMenu(orderMenuNameAndQuantity.split("-"));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ExceptionStatus.OCCURRED;
        }

        return ExceptionStatus.NOT_OCCURRED;
        order.validateOrder();
    }

    private String[] parseOrderInput(String orderInput) {
        return orderInput.split(",");
    }
}
