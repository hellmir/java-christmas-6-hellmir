package christmas.domain.event;

import christmas.dto.ChosenDateDto;
import christmas.validation.InputFormatValidator;

import java.util.Objects;

public class ChosenDate {
    private final int chosenDate;

    private ChosenDate(int chosenDate) {
        this.chosenDate = chosenDate;
    }

    public static ChosenDate from(String chosenDateInput) {
        InputFormatValidator.validateChosenDate(chosenDateInput);
        return new ChosenDate(Integer.parseInt(chosenDateInput));
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
}
