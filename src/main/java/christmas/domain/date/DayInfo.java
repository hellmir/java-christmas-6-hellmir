package christmas.domain.date;

public enum DayInfo {
    SUNDAY("일요일", "평일"),
    MONDAY("월요일", "평일"),
    TUESDAY("화요일", "평일"),
    WEDNESDAY("수요일", "평일"),
    THURSDAY("목요일", "평일"),
    FRIDAY("금요일", "주말"),
    SATURDAY("토요일", "주말");

    private final String koreanName;
    private final String weekDayOrWeekEnd;

    DayInfo(String koreanName, String weekDayOrWeekEnd) {
        this.koreanName = koreanName;
        this.weekDayOrWeekEnd = weekDayOrWeekEnd;
    }

    public boolean isWeekday() {
        return this.weekDayOrWeekEnd.equals("평일");
    }
}
