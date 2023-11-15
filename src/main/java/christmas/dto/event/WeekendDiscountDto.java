package christmas.dto.event;

public class WeekendDiscountDto {
    private final DiscountDto discountDto;

    public WeekendDiscountDto(DiscountDto discountDto) {
        this.discountDto = discountDto;
    }

    public DiscountDto getDiscountDto() {
        return discountDto;
    }
}
