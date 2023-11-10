package christmas.domain;

public enum Month {
    DECEMBER("12월", 12, 31);

    private final String description;
    private final int monthNumber;
    private final int lastDay;

    Month(String description, int monthNumber, int lastDay) {
        this.description = description;
        this.monthNumber = monthNumber;
        this.lastDay = lastDay;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
