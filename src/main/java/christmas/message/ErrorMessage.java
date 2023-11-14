package christmas.message;

import static christmas.configuration.OrderConfig.MAX_MENU_QUANTITY;

public class ErrorMessage {
    public static final String ERROR_MESSAGE_HEAD = "[ERROR] ";
    public static final String INVALID_DATE_EXCEPTION = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String INVALID_ORDER_EXCEPTION = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String MENU_QUANTITY_EXCEEDED_EXCEPTION
            = "총 메뉴 개수는 " + MAX_MENU_QUANTITY + "개를 초과할 수 없습니다. "
            + MAX_MENU_QUANTITY + "개 이하의 메뉴를 입력해 주세요.";
    public static final String ONLY_BEVERAGES_ORDERED_EXCEPTION
            = "주문 메뉴에는 반드시 음료를 제외한 메뉴가 한 개 이상 포함되어 있어야 합니다. 예: 아이스크림-1";

    public static final String PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION = "예상 결제 금액은 0 이하가 될 수 없습니다. 전송된 금액: ";
}
