package christmas.configuration;

import christmas.domain.order.MenuInformation;

import static christmas.configuration.PeriodConfig.YEAR;
import static christmas.domain.order.MenuInformation.CHAMPAGNE;

public class EventConfig {
    public static final int EVENT_APPLIED_AMOUNT = 10_000;
    public static final int GIVEAWAY_APPLIED_AMOUNT = 120_000;
    public static final MenuInformation GIVEAWAY_PRODUCT = CHAMPAGNE;
}
