package christmas.domain;

import christmas.validation.InputFormatValidator;

public class ChosenDate {
    private final int chosenDate;

    private ChosenDate(int chosenDate) {
        this.chosenDate = chosenDate;
    }

    public static ChosenDate from(String chosenDateInput) {
        InputFormatValidator.validateChosenDate(chosenDateInput);
        return new ChosenDate(Integer.parseInt(chosenDateInput));
    }
}
