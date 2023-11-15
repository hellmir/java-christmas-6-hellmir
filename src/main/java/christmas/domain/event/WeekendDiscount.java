package christmas.domain.event;

import christmas.dto.event.WeekendDiscountDto;

public class WeekendDiscount {
    private final Discount discount;

    public WeekendDiscount(Discount discount) {
        this.discount = discount;
    }

    public WeekendDiscountDto toDto() {
        return new WeekendDiscountDto(discount.toDto());
    }
}