package christmas.dto.event;

public class BenefitDto {
    private final int benefitAmount;

    public BenefitDto(int benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }
}
