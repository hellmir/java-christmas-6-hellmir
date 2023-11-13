package christmas.service;

import christmas.domain.Order;
import christmas.dto.ChosenDateDto;

public interface EventPlannerService {
    ChosenDateDto parseChosenDate(String chosenDateInput);

    Order generateOrder(String orderInput);
}
