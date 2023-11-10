package christmas.domain;

public class ChosenDate {
    private final int chosenDate;

    private ChosenDate(int chosenDate) {
        this.chosenDate = chosenDate;
    }

    public static ChosenDate from(String chosenDateInput) {
        return new ChosenDate(Integer.parseInt(chosenDateInput));
    }
}
