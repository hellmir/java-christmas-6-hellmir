package christmas.service;

import christmas.domain.ChosenDate;
import christmas.domain.Order;

public interface EventPlannerService {
    ChosenDate parseChosenDate(String chosenDateInput);

    Order generateOrder(String orderInput);
}
