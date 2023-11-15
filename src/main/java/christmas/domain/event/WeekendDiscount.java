package christmas.domain.event;

import christmas.domain.order.Order;
import christmas.domain.order.Payment;
import christmas.dto.event.WeekendDiscountDto;

import java.util.Objects;

public class WeekendDiscount {
    private final Discount discount;

    public WeekendDiscount() {
        discount = new Discount(0);
    }

    public WeekendDiscount(Discount discount) {
        this.discount = discount;
    }

    public static WeekendDiscount from(WeekendDiscountDto weekendDiscountDto) {
        Discount discount = Discount.from(weekendDiscountDto.getDiscountDto());
        return new WeekendDiscount(discount);
    }

    public static WeekendDiscount applyDiscount(Order order, Payment payment) {
        Discount discount = order.computeMainDiscount();
        discount.updateDiscountChange(payment);
        return new WeekendDiscount(discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeekendDiscount that = (WeekendDiscount) o;
        return Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount);
    }

    public WeekendDiscountDto toDto() {
        return new WeekendDiscountDto(discount.toDto());
    }
}
