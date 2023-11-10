package christmas.service;

import christmas.domain.ChosenDate;

public class EventPlannerServiceImpl implements EventPlannerService {
    @Override
    public ChosenDate parseChosenDate(String chosenDateInput) {
        return ChosenDate.from(chosenDateInput);
    }
}
