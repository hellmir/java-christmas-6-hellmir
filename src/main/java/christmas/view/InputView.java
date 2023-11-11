package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionHandler;
import christmas.exception.ExceptionStatus;

import static christmas.message.InfoMessage.DATE_CHOICE_MESSAGE;

public class InputView {
    private static ExceptionStatus exceptionStatus;

    public static String readDate() {
        String chosenDateInput;

        do {
            System.out.println(DATE_CHOICE_MESSAGE);
            chosenDateInput = Console.readLine();
            exceptionStatus = ExceptionHandler.handleDateInputException(chosenDateInput);
        } while (exceptionStatus.isOccurred());

        return chosenDateInput;
    }
}
