package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.message.InfoMessage.DATE_CHOICE_MESSAGE;
import static christmas.message.InfoMessage.MENUS_CHOICE_MESSAGE;

public class InputView {
    public static String readDate() {
        System.out.println(DATE_CHOICE_MESSAGE);
        return Console.readLine();
    }

    public static String readOrder() {
        System.out.println(MENUS_CHOICE_MESSAGE);
        return Console.readLine();
    }
}
