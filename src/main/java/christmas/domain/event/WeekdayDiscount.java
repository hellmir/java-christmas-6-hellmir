package christmas.domain.event;

import christmas.domain.order.Order;
import christmas.domain.order.Payment;
import christmas.dto.event.WeekdayDiscountDto;

import java.util.Objects;

public class WeekdayDiscount {
    private final Discount discount;

    public WeekdayDiscount() {
        discount = new Discount(0);
    }

    public WeekdayDiscount(Discount discount) {
        this.discount = discount;
    }

    public static WeekdayDiscount from(WeekdayDiscountDto weekdayDiscountDto) {
        Discount discount = Discount.from(weekdayDiscountDto.getDiscountDto());
        return new WeekdayDiscount(discount);
    }

    public static WeekdayDiscount applyDiscount(Order order, Payment payment) {
        Discount discount = order.computeDessertDiscount();
        discount.updateDiscountChange(payment);
        return new WeekdayDiscount(discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeekdayDiscount that = (WeekdayDiscount) o;
        return Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount);
    }

    public WeekdayDiscountDto toDto() {
        return new WeekdayDiscountDto(discount.toDto());
    }
}
