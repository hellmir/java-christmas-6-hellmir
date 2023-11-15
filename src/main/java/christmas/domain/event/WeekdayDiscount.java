package christmas.domain.event;

import christmas.dto.event.WeekdayDiscountDto;

public class WeekdayDiscount {
    private final Discount discount;

    public WeekdayDiscount() {
        discount = new Discount(0);
    }

    public WeekdayDiscount(Discount discount) {
        this.discount = discount;
    }

    public WeekdayDiscountDto toDto() {
        return new WeekdayDiscountDto(discount.toDto());
    }
}
