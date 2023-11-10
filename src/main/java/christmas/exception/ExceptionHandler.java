package christmas.exception;

import christmas.validation.InputFormatValidator;

public class ExceptionHandler {
    public static ExceptionStatus handleDateInputException(String chosenDateInput) {
        try {
            InputFormatValidator.validateChosenDate(chosenDateInput);
            return ExceptionStatus.NOT_OCCURRED;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ExceptionStatus.OCCURRED;
        }
    }
}
