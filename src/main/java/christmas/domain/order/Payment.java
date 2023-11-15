package christmas.domain.order;

import christmas.domain.event.ChristmasDiscount;
import christmas.domain.event.SpecialDiscount;
import christmas.domain.event.WeekdayDiscount;
import christmas.domain.event.WeekendDiscount;
import christmas.dto.order.PaymentDto;

import java.util.Objects;

import static christmas.configuration.EventConfig.EVENT_APPLIED_AMOUNT;
import static christmas.configuration.EventConfig.GIVEAWAY_APPLIED_AMOUNT;
import static christmas.message.ErrorMessage.ERROR_MESSAGE_HEAD;
import static christmas.message.ErrorMessage.PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION;

public class Payment {
    private int paymentAmount;

    public Payment(int paymentAmount) {
        validate(paymentAmount);
        this.paymentAmount = paymentAmount;
    }

    public static Payment from(PaymentDto paymentDto) {
        return new Payment(paymentDto.getPaymentAmount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentAmount == payment.paymentAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentAmount);
    }

    public boolean isEventApplied() {
        return paymentAmount >= EVENT_APPLIED_AMOUNT;
    }

    public PaymentDto toDto() {
        return new PaymentDto(paymentAmount);
    }

    public boolean isGiveawayApplied() {
        return paymentAmount >= GIVEAWAY_APPLIED_AMOUNT;
    }

    public void updateDiscountAmount(ChristmasDiscount christmasDiscount) {
        paymentAmount = christmasDiscount.reducePaymentAmount(paymentAmount);
        validate(paymentAmount);
    }

    public void updateDiscountAmount(WeekdayDiscount weekdayDiscount) {
        paymentAmount = weekdayDiscount.reducePaymentAmount(paymentAmount);
        validate(paymentAmount);
    }

    public void updateDiscountAmount(WeekendDiscount weekendDiscount) {
        paymentAmount = weekendDiscount.reducePaymentAmount(paymentAmount);
        validate(paymentAmount);
    }

    public void updateDiscountAmount(SpecialDiscount specialDiscount) {
        paymentAmount = specialDiscount.reducePaymentAmount(paymentAmount);
        validate(paymentAmount);
    }

    private void validate(int paymentAmount) {
        if (paymentAmount < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION + paymentAmount);
        }
    }
}
