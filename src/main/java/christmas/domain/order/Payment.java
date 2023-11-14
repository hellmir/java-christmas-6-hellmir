package christmas.domain.order;

import christmas.dto.PaymentDto;

import java.util.Objects;

import static christmas.configuration.EventConfig.EVENT_APPLIED_AMOUNT;

public class Payment {
    private int payment;

    public Payment(int payment) {
        this.payment = payment;
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
}