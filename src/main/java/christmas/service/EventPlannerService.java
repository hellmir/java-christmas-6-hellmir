package christmas.service;

import christmas.dto.ChosenDateDto;
import christmas.dto.OrderDto;

public interface EventPlannerService {
    ChosenDateDto parseChosenDate(String chosenDateInput);

    OrderDto generateOrder(String orderInput);
}
