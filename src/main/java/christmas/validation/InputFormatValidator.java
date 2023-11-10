package christmas.validation;

import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static christmas.message.ErrorMessage.INVALID_DATE_EXCEPTION;

public class InputFormatValidator {
    public static void validateChosenDate(String chosenDateInput) {
        validateChosenDateNumberFormat(chosenDateInput);

        int chosenDate = Integer.parseInt(chosenDateInput);
        validateChosenDateRange(chosenDate);
    }

    private static void validateChosenDateNumberFormat(String chosenDateInput) {
        if (chosenDateInput.matches("[1-9][0-9]*")) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + INVALID_DATE_EXCEPTION);
    }

    private static void validateChosenDateRange(int chosenDate) {
        if (1 <= chosenDate && chosenDate <= 31) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + INVALID_DATE_EXCEPTION);
    }
}
