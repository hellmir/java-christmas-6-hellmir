package christmas.configuration;

import christmas.domain.Month;

import static christmas.domain.Month.DECEMBER;

public class PeriodConfig {
    public static final int year = 2023;
    public static final Month month = DECEMBER;

    public int getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }
}
