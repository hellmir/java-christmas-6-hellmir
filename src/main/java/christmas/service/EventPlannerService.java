package christmas.service;

import christmas.domain.ChosenDate;

public interface EventPlannerService {
    ChosenDate parseChosenDate(String chosenDateInput);
}
