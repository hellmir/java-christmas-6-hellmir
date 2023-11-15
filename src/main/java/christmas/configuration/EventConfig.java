package christmas.configuration;

import christmas.domain.order.MenuInformation;

import static christmas.configuration.PeriodConfig.YEAR;
import static christmas.domain.order.MenuInformation.CHAMPAGNE;

public class EventConfig {
    public static final int EVENT_APPLIED_AMOUNT = 10_000;
    public static final int GIVEAWAY_APPLIED_AMOUNT = 120_000;
    public static final MenuInformation GIVEAWAY_PRODUCT = CHAMPAGNE;
    public static final int BASIC_CHRISTMAS_DISCOUNT_AMOUNT = 1_000;
    public static final int CHRISTMAS_DISCOUNT_INCREASE = 100;
    public static final int WEEKDAY_DISCOUNT_AMOUNT = YEAR;
    public static final int WEEKEND_DISCOUNT_AMOUNT = YEAR;
    public static final int SPECIAL_DAY_DISCOUNT_AMOUNT = 1_000;
}
