package christmas.dto.event;

public class SpecialDiscountDto {
    private final DiscountDto discountDto;

    public SpecialDiscountDto(DiscountDto discountDto) {
        this.discountDto = discountDto;
    }

    public DiscountDto getDiscountDto() {
        return discountDto;
    }
}
