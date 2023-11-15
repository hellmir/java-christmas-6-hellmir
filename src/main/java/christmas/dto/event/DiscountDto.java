package christmas.dto.event;

public class DiscountDto {
    private final int discountAmount;

    public DiscountDto(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
