package christmas.message;

import christmas.configuration.PeriodConfig;
import christmas.domain.order.MenuInformation;

public class InfoMessage {
    public static final String STARTING_INFO_MESSAGE = "안녕하세요! 우테코 식당 "
            + PeriodConfig.MONTH.getMonthNumber() + "월 이벤트 플래너입니다.";
    public static final String DATE_CHOICE_MESSAGE = PeriodConfig.MONTH.getMonthNumber()
            + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String MENUS_CHOICE_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. "
            + MenuInformation.SEAFOOD_PASTA.getKoreanName() + "-2,"
            + MenuInformation.RED_WINE.getKoreanName() + "-1,"
            + MenuInformation.CHOCOLATE_CAKE.getKoreanName() + "-1)";

    public static final String FIRST_RESULT_MESSAGE_HEAD = PeriodConfig.MONTH.getMonthNumber() + "월 ";
    public static final String SECOND_RESULT_MESSAGE_HEAD = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String ORDER_MENUS_MESSAGE_HEAD = "<주문 메뉴>";
}
