package christmas.dto.event;

public class ChosenDateDto {
    private final int chosenDate;

    public ChosenDateDto(int chosenDate) {
        this.chosenDate = chosenDate;
    }

    public int getChosenDate() {
        return chosenDate;
    }
}
