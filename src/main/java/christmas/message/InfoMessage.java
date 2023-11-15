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
    public static final String TOTAL_ORDER_PRICE_MESSAGE_HEAD = "<할인 전 총주문 금액>";
    public static final String GIVEAWAY_MESSAGE_HEAD = "<증정 메뉴>";
    public static final String NONE = "없음";
    public static final String BENEFIT_LIST_MESSAGE_HEAD = "<혜택 내역>";
    public static final String CHRISTMAS_BENEFIT_MESSAGE = "크리스마스 디데이 할인: -";
    public static final String WEEKDAY_BENEFIT_MESSAGE = "평일 할인: -";
    public static final String WEEKEND_BENEFIT_MESSAGE = "주말 할인: -";
    public static final String SPECIAL_BENEFIT_MESSAGE = "특별 할인: -";
    public static final String GIVEAWAY_BENEFIT_MESSAGE = "증정 이벤트: -";
    public static final String BENEFIT_AMOUNT_MESSAGE_HEAD = "<총혜택 금액>";
    public static final String EXPECTED_PAYMENT_MESSAGE_HEAD = "<할인 후 예상 결제 금액>";
    public static final String BADGE_MESSAGE_HEAD = "<12월 이벤트 배지>";

}
