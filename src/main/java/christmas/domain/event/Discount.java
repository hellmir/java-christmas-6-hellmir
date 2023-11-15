package christmas.domain.event;

import christmas.dto.event.DiscountDto;

import java.util.Objects;

import static christmas.message.ErrorMessage.DISCOUNT_AMOUNT_UNDER_ZERO_EXCEPTION;
import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;

public class Discount {
    private final int discountAmount;

    public Discount(int discountAmount) {
        validate(discountAmount);
        this.discountAmount = discountAmount;
    }

    public static Discount from(DiscountDto discountDto) {
        return new Discount(discountDto.getDiscountAmount());
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

    public DiscountDto toDto() {
        return new DiscountDto(discountAmount);
    }

    public int reducePaymentAmount(int paymentAmount) {
        return paymentAmount - discountAmount;
    }

    private void validate(int discountAmount) {
        if (discountAmount < 0) {
            throw new IllegalArgumentException(
                    ERROR_MESSAGE_HEAD + DISCOUNT_AMOUNT_UNDER_ZERO_EXCEPTION + discountAmount
            );
        }
    }
}
