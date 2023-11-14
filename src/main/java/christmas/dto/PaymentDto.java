package christmas.dto;

public class PaymentDto {
    private int paymentAmount;

    public PaymentDto(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }
}
