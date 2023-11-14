package christmas.domain.order;

import christmas.dto.PaymentDto;

import java.util.Objects;

import static christmas.configuration.EventConfig.EVENT_APPLIED_AMOUNT;
import static christmas.configuration.EventConfig.GIVEAWAY_APPLIED_AMOUNT;

public class Payment {
    private int payment;

    public Payment(int payment) {
        this.payment = payment;
    }

    public static Payment from(PaymentDto paymentDto) {
        return new Payment(paymentDto.getPayment());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return payment == payment1.payment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment);
    }

    public boolean isEventApplied() {
        return payment >= EVENT_APPLIED_AMOUNT;
    }

    public PaymentDto toDto() {
        return new PaymentDto(payment);
    }

    public boolean isGiveawayApplied() {
        return payment >= GIVEAWAY_APPLIED_AMOUNT;
    }
}
