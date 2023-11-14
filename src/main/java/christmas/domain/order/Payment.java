package christmas.domain.order;

import christmas.dto.PaymentDto;

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

    private void validate(int paymentAmount) {
        if (paymentAmount < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + PAYMENT_AMOUNT_UNDER_ZERO_EXCEPTION + paymentAmount);
        }
    }
}
