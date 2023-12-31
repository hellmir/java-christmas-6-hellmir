package christmas.domain.event;

import christmas.domain.order.Payment;
import christmas.dto.event.SpecialDiscountDto;

import java.util.Objects;

import static christmas.configuration.EventConfig.SPECIAL_DAY_DISCOUNT_AMOUNT;

public class SpecialDiscount {
    private final Discount discount;

    public SpecialDiscount() {
        discount = new Discount(0);
    }

    public SpecialDiscount(Discount discount) {
        this.discount = discount;
    }

    public static SpecialDiscount from(SpecialDiscountDto specialDiscountDto) {
        Discount discount = Discount.from(specialDiscountDto.getDiscountDto());
        return new SpecialDiscount(discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialDiscount that = (SpecialDiscount) o;
        return Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount);
    }

    public SpecialDiscountDto toDto() {
        return new SpecialDiscountDto(discount.toDto());
    }

    public static SpecialDiscount applyDiscount(Payment payment, Benefit benefit) {
        Discount discount = new Discount(SPECIAL_DAY_DISCOUNT_AMOUNT);
        discount.updateDiscountChange(payment, benefit);
        return new SpecialDiscount(discount);
    }
}
