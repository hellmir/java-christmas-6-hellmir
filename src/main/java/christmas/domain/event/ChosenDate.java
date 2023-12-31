package christmas.domain.event;

import christmas.domain.date.DayInfo;
import christmas.dto.event.ChosenDateDto;
import christmas.validation.InputFormatValidator;

import java.time.LocalDate;
import java.util.Objects;

import static christmas.configuration.EventConfig.BASIC_CHRISTMAS_DISCOUNT_AMOUNT;
import static christmas.configuration.EventConfig.CHRISTMAS_DISCOUNT_INCREASE;
import static christmas.configuration.PeriodConfig.MONTH;
import static christmas.configuration.PeriodConfig.YEAR;

public class ChosenDate {
    private final int chosenDate;

    private ChosenDate(int chosenDate) {
        this.chosenDate = chosenDate;
    }

    public static ChosenDate from(String chosenDateInput) {
        InputFormatValidator.validateChosenDate(chosenDateInput);
        return new ChosenDate(Integer.parseInt(chosenDateInput));
    }

    public static ChosenDate from(ChosenDateDto chosenDateDto) {
        return new ChosenDate(chosenDateDto.getChosenDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChosenDate that = (ChosenDate) o;
        return chosenDate == that.chosenDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chosenDate);
    }

    public ChosenDateDto toDto() {
        return new ChosenDateDto(chosenDate);
    }

    public Discount updateChristmasDiscountAmount() {
        if (chosenDate > 25) {
            return new Discount(0);
        }

        int basicDiscountAmount = BASIC_CHRISTMAS_DISCOUNT_AMOUNT;
        int increasedDiscountAmount = CHRISTMAS_DISCOUNT_INCREASE * (chosenDate - 1);
        return new Discount(basicDiscountAmount + increasedDiscountAmount);
    }

    public boolean isWeekday() {
        LocalDate localDate = LocalDate.of(YEAR, MONTH.getMonthNumber(), chosenDate);
        String DayOfWeek = String.valueOf(localDate.getDayOfWeek());

        return DayInfo.valueOf(DayOfWeek).isWeekday();
    }

    public boolean isSpecialDay() {
        if (chosenDate == 25 || chosenDate % 7 == 3) {
            return true;
        }

        return false;
    }
}
