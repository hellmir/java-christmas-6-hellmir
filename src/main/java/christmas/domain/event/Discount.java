package christmas.domain.event;

import java.util.Objects;

public class Discount {
    private final int discountAmount;

    public Discount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return discountAmount == discount.discountAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountAmount);
    }

    public int reducePaymentAmount(int paymentAmount) {
        return paymentAmount - discountAmount;
    }
}
