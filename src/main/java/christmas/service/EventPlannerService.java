package christmas.service;

import christmas.domain.ChosenDate;
import christmas.domain.Order;
import christmas.exception.ExceptionStatus;

public interface EventPlannerService {
    ChosenDate parseChosenDate(String chosenDateInput);

    ExceptionStatus takeOrder(String orderInput, Order order);
}
