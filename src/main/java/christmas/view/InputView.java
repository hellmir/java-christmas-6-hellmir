package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.message.InfoMessage.DATE_CHOICE_MESSAGE;

public class InputView {
    public static String readDate() {
        System.out.println(DATE_CHOICE_MESSAGE);
        return Console.readLine();
    }
}
