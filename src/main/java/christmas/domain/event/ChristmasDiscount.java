package christmas.domain.event;

import christmas.dto.event.ChristmasDiscountDto;

import java.util.Objects;

public class ChristmasDiscount {
    private final Discount discount;

    public ChristmasDiscount() {
        discount = new Discount(0);
    }

    private ChristmasDiscount(Discount discount) {
        this.discount = discount;
    }

    public static ChristmasDiscount from(ChosenDate chosenDate) {
        Discount discount = chosenDate.updateChristmasDiscountAmount();
        return new ChristmasDiscount(discount);
    }

    public static ChristmasDiscount from(ChristmasDiscountDto christmasDiscountDto) {
        Discount discount = Discount.from(christmasDiscountDto.getDiscountDto());
        return new ChristmasDiscount(discount);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChristmasDiscount that = (ChristmasDiscount) o;
        return Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount);
    }

    public ChristmasDiscountDto toDto() {
        return new ChristmasDiscountDto(discount.toDto());
    }

    public int reducePaymentAmount(int paymentAmount) {
        return discount.reducePaymentAmount(paymentAmount);
    }
}
